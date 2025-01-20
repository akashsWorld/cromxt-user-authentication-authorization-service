$("#toggle-password").click(function () {
  const passwordField = $("#password");
  const passwordFieldType = passwordField.attr("type");
  if (passwordFieldType === "password") {
    passwordField.attr("type", "text");
    $(this).find("i").removeClass("fa-eye").addClass("fa-eye-slash");
  } else {
    passwordField.attr("type", "password");
    $(this).find("i").removeClass("fa-eye-slash").addClass("fa-eye");
  }
});

$("#avatar").on("change", function () {
  const file = this.files[0];
  const reader = new FileReader();
  reader.onload = function (e) {
    const preview = document.getElementById("avatar-preview");
    const cameraIcon = document.getElementById("camera-icon");
    cameraIcon.style.display = "none";
    preview.style.backgroundImage = `url(${e.target.result})`;
  };

  reader.readAsDataURL(file);
});
$("#toggle-confirm-password").click(function () {
  const passwordField = $("#confirm-password");
  const passwordFieldType = passwordField.attr("type");
  if (passwordFieldType === "password") {
    passwordField.attr("type", "text");
    $(this).find("i").removeClass("fa-eye").addClass("fa-eye-slash");
  } else {
    passwordField.attr("type", "password");
  }
});

$("#register-form").submit(async function (form) {
  form.preventDefault();
  const avatar = $("#avatar");
  console.log(avatar);
  if (!avatar) {
    $("#avatar-preview").addClass("error");
    return;
  }
  const password = $("#password");
  const confirmPassword = $("#confirm-password");
  
  if (password.val() !== confirmPassword.val()) {
    $("#confirm-password_input-box").addClass("error");
    confirmPassword.focus();
    return;
  }
  const gender = $("#Gender");

  if(gender.val()===null){
    gender.focus();
    $('#gender-input').addClass('error');
    return;
  }

  const formData = new FormData(this);
  formData.append("gender",gender.val());
  const url = this.action;

  const response = await fetch(url, {
    method: "POST",
    body: formData,
  });
  if (response.redirected === true) {
    window.location.href = response.url;
  }
});
