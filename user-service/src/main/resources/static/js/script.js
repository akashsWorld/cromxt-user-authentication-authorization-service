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

$('register-form').on('submit',(form)=>{
    form.preventDefault();
    const password = $('#password').val();
    const confirmPassword = $('#confirm-password').val();
    console.log("password: "+password+" confirmPassword: "+confirmPassword);
    if(password !== confirmPassword){
        $('password_input').style.border = '1px solid red';
        $('confirm_password_input').style.border = '1px solid red';
    }else {
        form.submit();
    }
})

