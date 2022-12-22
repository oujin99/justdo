package com.yujeans.justdo.api.kakao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KakaoService {

	// 토큰 요청 , 반환하는 메소드
	public String getToken(String code) {
		
		String token="";
		
		try {
			
			// 토큰 요청 url (post 방식으로 필수 파라미터를 넣어 요청하면 카카오측에서 json 데이터를 반환해준다.)
			String requestTokenURL = "https://kauth.kakao.com/oauth/token";
			URL url = new URL(requestTokenURL);
			
			// 카카오에서 요구하는 요청 URL로 HttpURLConnection 연결
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			
			// output Stream 연결
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            
            // 카카오에서 요구하는 필수 파라미터들
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=14fc3afe3224a3ab568b44392a0f3115");
            sb.append("&redirect_uri=http://localhost:9090/kakao/login");
            sb.append("&code=" + code);
            
            bw.write(sb.toString());
            bw.flush();
            
            int responseCode = conn.getResponseCode();
//            System.out.println("responseCode = " + responseCode);
            
            
            // Post 방식으로 필수 요구 파라미터 날리면 카카오쪽에서 json으로 필요한 값을 반환해준다고 한다.
            // inputStream으로 반환받은 json을 가져와서 parsing해줘야 한다.
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
//            System.out.println("result = " + result);

            // json parsing
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(result);

            String access_token = obj.get("access_token").toString();
            String refresh_token = obj.get("refresh_token").toString();
//            System.out.println("refresh_token = " + refresh_token);
//            System.out.println("access_token = " + access_token);

            token = access_token;

            br.close();
            bw.close();
			
		} catch (MalformedURLException e) {
			System.out.println(":::: URL 커넥트 에러");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(":::: 인풋 아웃풋 도중 에러");
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println(":::: 제이슨 파싱 에러");
			e.printStackTrace();
		}
		
		return token;
	}
	
	// 토큰으로 유저정보 얻어서 반환해주는 메소드
	public HashMap<String, Object> getUserInfo(String token) {
		
		String host = "https://kapi.kakao.com/v2/user/me";
        HashMap<String, Object> result = new HashMap<>();
        try {
            URL url = new URL(host);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Authorization", "Bearer " + token);
            urlConnection.setRequestMethod("GET");

            int responseCode = urlConnection.getResponseCode();
//            System.out.println("responseCode = " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = "";
            String res = "";
            while((line=br.readLine())!=null)
            {
                res+=line;
            }

//            System.out.println("res = " + res);
            
            // res에 들어있는 데이터
            // 		: id , connected_at , properties{nickname, profile_image, thumbnail_image} ,
            //			kakao_account{profile{nickname, thumbnail_image_url, profile_image_url}, email}

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(res);
            JSONObject properties = (JSONObject) obj.get("properties");
            JSONObject kakao_account = (JSONObject) obj.get("kakao_account");

            String id = obj.get("id").toString();
            String nickname = properties.get("nickname").toString();
            String profile_image = properties.get("profile_image").toString();
            String thumbnail_image = properties.get("thumbnail_image").toString();
            String email = kakao_account.get("email").toString();

            result.put("id", id);
            result.put("nickname", nickname);
            result.put("profile_image", profile_image);
            result.put("thumbnail_image", thumbnail_image);
            result.put("email", email);

            br.close();


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return result;
	}
	
	// 유저가 입력한 동의 정보 메소드
	public String getAgreementInfo(String access_token) {
		
        String result = "";
        String host = "https://kapi.kakao.com/v2/user/scopes";
        try{
            URL url = new URL(host);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Authorization", "Bearer "+access_token);

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = "";
            while((line=br.readLine())!=null)
            {
                result+=line;
            }
            
//            System.out.println("result(agreement) : "+result);

            int responseCode = urlConnection.getResponseCode();
//            System.out.println("responseCode = " + responseCode);

            br.close();
            
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(result);
            JSONArray scopes = (JSONArray) obj.get("scopes");
            
            for(int i=0; i<scopes.size(); i++) {
            	JSONObject scope = (JSONObject) scopes.get(i);
            	if(scope.get("id").equals("account_email")) {
//            		System.out.println("using : "+scope.get("using"));
            	}
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			e.printStackTrace();
		}
        
        return result;
    }

}
