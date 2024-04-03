var swiper = new Swiper(".doctor-slider", {
    loop:true,
    spaceBetween: 20,
    autoplay: {
        delay: 7500,
        disableOnInteraction: false,
    },
    centeredSlides:true,
    breakpoints: {
      0: {
        slidesPerView: 1,
      },
      768: {
        slidesPerView: 2,
      },
      1020: {
        slidesPerView: 3,
      },
    },
  });

document.querySelector(".login-btn").addEventListener("click", () => {
  window.location.href = "../Login_Page/index.html";
});


// Navigate Feature Button 
document.querySelector(".patient_info").addEventListener("click", () => {
  window.location.href = "../Manage_Patient/List_Patient/index.html";
});

document.querySelector(".schedule_info").addEventListener("click", () => {
    window.location.href = "../Schedule/index.html";
});

document.querySelector(".doctor_info").addEventListener("click", () => {
    window.location.href = "../Manage_Doctor/Doctor_list/doctor.html";
})

document.querySelector(".tool_info").addEventListener("click", () => {
    window.location.href = "../Manage_Tool/devicesform.html";
})

document.querySelector(".medicine_info").addEventListener("click", () => {
    window.location.href = "../Manage_Medicine/medicineform.html";
})

