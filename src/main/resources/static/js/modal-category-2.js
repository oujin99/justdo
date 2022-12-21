$(function() {

    $('.toggles button').click(function(){
      var get_id = this.id;
      var get_current = $('.categorys .' + get_id);
    
        $('.category').not( get_current ).hide(500);
        get_current.show(300);
    });

    
    $('#showall').click(function() {
        $('.category').show(200);
    });

    
    // $("button").click(function() {
    //     toggleClass(".active-color");
    // });


});