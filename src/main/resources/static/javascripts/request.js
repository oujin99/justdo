function viewMore(){
    const more = document.getElementsByClassName("more");
    const moreContainer = document.getElementsByClassName("request-container")[0];
    
    moreContainer.setAttribute("class", "more-container");
    
    for(i of more){
        i.style.display = "inline";
    }

    document.getElementsByClassName("btn-secondary")[0].style.display="none";
}

function closeAd(){
    const appAd=document.getElementsByClassName("app-advertisement");
    appAd[0].style.display="none";
}

function numKeyCheck(){
    if(event.keyCode<48 || event.keyCode>57){
        event.returnValue = false;
    } else {
        event.returnValue = true;
    }
}

function autoHypenPhone(str){
    str = str.replace(/[^0-9]/g, '');
      let tmp = '';
      
      if(str.length < 4){
          return str;
      }else if(str.length < 7){
          tmp += str.substr(0, 3);
          tmp += '-';
          tmp += str.substr(3);
          return tmp;
      }else if(str.length < 11){
          tmp += str.substr(0, 3);
          tmp += '-';
          tmp += str.substr(3, 3);
          tmp += '-';
          tmp += str.substr(6);
          return tmp;
      }else{              
          tmp += str.substr(0, 3);
          tmp += '-';
          tmp += str.substr(3, 4);
          tmp += '-';
          tmp += str.substr(7);
          return tmp;
      }
  
      return str;
}

const fc = document.getElementById("form-control")
fc.addEventListener('input', function(event){
    numKeyCheck();
    fc.value = autoHypenPhone(event.target.value);
})

