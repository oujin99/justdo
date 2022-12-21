    ```java
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
            System.out.println("responseCode = " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = "";
            String res = "";
            while((line=br.readLine())!=null)
            {
                res+=line;
            }

            System.out.println("res = " + res);
            
            // res에 들어있는 데이터
            //       : id , connected_at , properties{nickname, profile_image, thumbnail_image} ,
            //         kakao_account{profile{nickname, thumbnail_image_url, profile_image_url}, email}

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
   ```