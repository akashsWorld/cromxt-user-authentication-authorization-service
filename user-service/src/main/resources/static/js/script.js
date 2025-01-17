$('#toggle-password').click(function () {
    const passwordField = $('#password');
    const passwordFieldType = passwordField.attr('type');
    if (passwordFieldType === 'password') {
        passwordField.attr('type', 'text');
        $(this).find('i').removeClass('fa-eye').addClass('fa-eye-slash');
    } else {
        passwordField.attr('type', 'password');
        $(this).find('i').removeClass('fa-eye-slash').addClass('fa-eye');
    }
});

$('#avatar').on('change', function () {
    const file = this.files[0];
    const reader = new FileReader();
    reader.onload = function (e) {
        const preview = document.getElementById('avatar-preview');
        const cameraIcon = document.getElementById('camera-icon');
        cameraIcon.style.display = 'none';
        preview.style.backgroundImage = `url(${e.target.result})`;
    };

    reader.readAsDataURL(file);
})
$('#toggle-confirm-password').click(function () {
    const passwordField = $('#confirm-password');
    const passwordFieldType = passwordField.attr('type');
    if (passwordFieldType === 'password') {
        passwordField.attr('type', 'text');
        $(this).find('i').removeClass('fa-eye').addClass('fa-eye-slash');
    } else {
        passwordField.attr('type', 'password');
    }
});

$('#register-form').submit(function (form){
    form.preventDefault();
    const avatar = $('#avatar').files[0];
    console.log(avatar);
    if(!avatar){
        $('#avatar-preview').addClass('error');
        return;
    }
    const password = $('#password').val();
    const confirmPassword = $('#confirm-password').val();
    console.log(password, confirmPassword);
    if (password !== confirmPassword) {
        password.addClass('error');
        confirmPassword.addClass('error');

        confirmPassword.focus();
        password.focus();
    }else{
        form.submit();
    }
})


