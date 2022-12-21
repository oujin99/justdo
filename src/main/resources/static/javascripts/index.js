const toggleBtn = document.querySelector('.account_toggleBtn');
const idbar = document.querySelector('.idbar');

toggleBtn.addEventListener('click', () => {
    idbar.classList.toggle('active');
});