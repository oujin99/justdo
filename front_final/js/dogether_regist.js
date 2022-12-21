/* -------------------- 두게더 등록 js 시작 -------------------- */

/* 썸네일 카드 클릭시 input(file) 클릭효과 */
let dogether_image = $("#dogether_image");
$("#image_card1").on("click", function(e){
    console.log("눌렀어");
    dogether_image.click();
    
});

/* 커버 이미지 선택시 미리보기 */
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
    
        reader.onload = function (e) {
            $('#dogether_image_img').attr('src', e.target.result);  
            console.log(e.target.result);
        }
        

        reader.readAsDataURL(input.files[0]);
    }
}
 
$("#dogether_image").change(function(){
    readURL(this);
});

/* -------------------- 두게더 등록 js 끝 -------------------- */