    // Get the modal
    var modal_edit = document.getElementById("myModal_edit");

    // Get the button that opens the modal
    var btn_edit = document.getElementById("myBtn_edit");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks on the button, open the modal
    btn_edit.onclick = function() {
    modal_edit.style.display = "block";
}

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
    modal.style.display = "none";
}

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
    if (event.target == modal_edit) {
        modal_edit.style.display = "none";
    }
}