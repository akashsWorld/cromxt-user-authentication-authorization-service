const findEmail = () =>{
    const email = $('#account-email').val();
    if(email === ""){
        return;
    }
    const form = $('#find-account-form');
    form.submit();
}
$('#account-email').on('input',(eve)=>{
    if(eve.keyCode === 13){
        findEmail();
    }
});