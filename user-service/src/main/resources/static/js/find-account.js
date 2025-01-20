const findEmail = () =>{
    const email = $('#account-email').val();
    if(email === "" || email === null || email === undefined || email.length <= 5){
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

$('#search-icon').on('click',()=>{
    findEmail();
});