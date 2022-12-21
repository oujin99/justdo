const openModalBtn = document.querySelectorAll('.openModal')
const overlay = document.getElementById('overlay')
const logOutBtn = document.querySelector('#logOut')

openModalBtn.forEach(btn => {
  btn.addEventListener('click', () => {
  openModal(modal)
  })
})

overlay.addEventListener('click', () => {
  const modals = document.querySelectorAll('.modal.active')
  modals.forEach(modal => {
    closeModal(modal)
  })
})

function openModal(modal) {
  if (modal == null) return
  modal.classList.add('active')
  overlay.classList.add('active')
}

function closeModal(modal) {
  if (modal == null) return
  modal.classList.remove('active')
  overlay.classList.remove('active')
}

