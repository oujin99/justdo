const searchPc = document.querySelector('.h_search input');
const searchPcBorder = document.querySelector('.h_search');
const searchPcWrap = document.querySelector('.h_search .search_tab_wrap');
const closeBtnPc = document.querySelector('.h_search .search_tab_wrap .tab_close');
const searchMob = document.querySelector('.search_wrap .search input');
const searchIconMob = document.querySelector('.tab_mob_header .h_search img');
const searchMobWrap = document.querySelector('.search_wrap .search_tab_wrap');
const backBtnMob = document.querySelector('.search_wrap .search_tab_wrap .back_icon');
const closeTabBtnMob = document.querySelector('.search_wrap .search_tab_wrap .tab_close');
const tabItem = document.querySelectorAll('.tab_keyword .item');
const tabTitle = document.querySelector('.tab_title ul>li>a');
const menuIconMob = document.querySelector('.tab_mob_header .h_inner .menu');
const menuWrap = document.querySelector('.tab_mob_header .h_inner .menu_wrap');
const closeMenu = document.querySelector('.tab_mob_header .h_inner .menu_wrap .close_btn img');
const body = document.querySelector('body')

menuIconMob.addEventListener("click", function(){
  menuWrap.classList.add('active');
});

closeMenu.addEventListener("click", function(){
  menuWrap.classList.remove('active');
});

searchPc.addEventListener("click", function (){
  searchPcWrap.classList.add('active')
  searchPcBorder.classList.add('active');

});

closeBtnPc.addEventListener("click", function (){
  searchPcWrap.classList.remove('active')
  searchPcBorder.classList.remove('active');
});

function showSearchMob(){
  searchMobWrap.classList.add('active')
  body.classList.add('active');
};
searchIconMob.addEventListener("click",showSearchMob);
searchMob.addEventListener("click", showSearchMob);

closeTabBtnMob.addEventListener("click", function (){
  searchMobWrap.classList.remove('active')
  body.classList.remove('active');
});
backBtnMob.addEventListener("click", function (){
  searchMobWrap.classList.remove('active')
  body.classList.remove('active');
});


for(let i=0; i<tabItem.length; i++){
  tabItem[i].addEventListener("mouseover", function(){
    tabItem[i].classList.add('active');
  });
  tabItem[i].addEventListener("mouseout", function(){
    tabItem[i].classList.remove('active');
  });
};

/* slider */
let bannerS = new Swiper(".bannerS", {
  slidesPerView: 1,
  spaceBetween: 30,
  loop: true,
  autoplay: {delay: 5000},
  navigation: {
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev"
  },
});

let main_slider = new Swiper(".main_slider", {
  slidesPerView: 2,
  spaceBetween: 8,
  slidesPerGroup: 1,
  navigation: {
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev"
  },
  breakpoints: {
    481: {
      slidesPerView: 3,  
      spaceBetween: 12,
      slidesPerGroup: 1,
    },
    991: {
      slidesPerView: 4, 
      spaceBetween: 16,
      slidesPerGroup: 2,
    },
  },
});

let b_pro_S = new Swiper(".b_pro_S", {
  slidesPerView: 3,
  spaceBetween: 8,
  slidesPerGroup: 1,
  navigation: {
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev"
  },
  breakpoints: {
    481: {
      slidesPerView: 4, 
      spaceBetween: 12,
      slidesPerGroup: 1,
    },
    769: {
      slidesPerView: 5, 
      spaceBetween: 12,
      slidesPerGroup: 1,
    },
    991: {
      slidesPerView: 6, 
      spaceBetween: 16,
      slidesPerGroup: 2,
    },
  },
});

let story_S = new Swiper(".story_S", {
  slidesPerView: 2,
  spaceBetween: 8,
  slidesPerGroup: 1,
  navigation: {
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev"
  },
  breakpoints: {
    769: {
      slidesPerView: 3,
      spaceBetween: 16,
      slidesPerGroup: 1,
    },
    991: {
      slidesPerView: 3, 
      spaceBetween: 16,
      slidesPerGroup: 1,
    },
  },
});

let ps_S = new Swiper(".ps_S", {
  spaceBetween: 30,
  hashNavigation: {
    watchState: true,
  },
  pagination: {
    el: ".swiper-pagination",
    clickable: true,
  },
  navigation: {
    nextEl: ".ps_S_next",
    prevEl: ".ps_S_prev",
  },
});

