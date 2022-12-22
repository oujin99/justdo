// const button = document.querySelector("#btn");
const button1 = document.querySelector("#btn1");
const button2 = document.querySelector("#btn2");
const button3 = document.querySelector("#btn3");
const button4 = document.querySelector("#btn4");
const button5 = document.querySelector("#btn5");
const button6 = document.querySelector("#btn6");
// const button7 = document.querySelector("#btn7");
const modal = document.querySelector(".modal-wrap");
const modalBackgroundDiv = modal.querySelector(".modal-background");

function modalToggle(){
    modal.classList.toggle("show");
}

// button.addEventListener("click", () => {
//     modalToggle();
// });
button1.addEventListener("click", () => {
    modalToggle();
});
button2.addEventListener("click", () => {
    modalToggle();
});
button3.addEventListener("click", () => {
    modalToggle();
});
button4.addEventListener("click", () => {
    modalToggle();
});
button5.addEventListener("click", () => {
    modalToggle();
});
button6.addEventListener("click", () => {
    modalToggle();
});
// button7.addEventListener("click", () => {
//     modalToggle();
// });

modalBackgroundDiv.addEventListener("click", () => {
    modalToggle();
});

