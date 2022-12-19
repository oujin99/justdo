const gosu_Slide = document.querySelector(".slider_content");
const gosu_content = document.querySelectorAll(".content_1");
const dot = document.querySelectorAll(".slider_controller li");
//Buttons
const prevBtn = document.querySelector("#prevBtn");
const nextBtn = document.querySelector("#nextBtn");

//Counter
let counter = 0;
const size = gosu_content[0].clientWidth;

gosu_Slide.style.transform = "translateX(" + -size * counter + "px)";

nextBtn.addEventListener("click", () => {
  if (counter >= gosu_content.length - 1) return;
  gosu_Slide.style.transition = "transform 0.4s ease-in-out";
  dot[counter].style.color = "gray";
  counter++;
  gosu_Slide.style.transform = "translateX(" + -size * counter + "px)";
  // dot.style.color = "grey";
  dot[counter].style.color = "black";
});

prevBtn.addEventListener("click", () => {
  if (counter <= 0) return;
  gosu_Slide.style.transition = "transform 0.4s ease-in-out";
  dot[counter].style.color = "gray";
  counter--;
  gosu_Slide.style.transform = "translateX(" + -size * counter + "px)";
  // dot.style.color = "grey";
  dot[counter].style.color = "black";
});
