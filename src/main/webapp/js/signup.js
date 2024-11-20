const form = document.formSignup;

const ID_REGEX = /^[A-Za-z][A-Za-z0-9]{3,}$/;
const PASSWORD_REGEX = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()_+={}|\[\]:;"'<>,.?/\\~`-]).{8,}$/;
const PHONE_REGEX = /^0\d{8,11}$/;

let idConfirmed = false;

function raiseError(input, message){
	alert(message);
	input.focus();
}
function validate(){
	
	if(! ID_REGEX.test(form.id.value)){
		raiseError(form.id, "ID should be longer than 3 characters, should only contain alphabet or number, starts with alphabet")
		return false;
	}
	if(! idConfirmed){
		raiseError(form.idCheck, "Please press 'ID check' first")
		return false;
	}
	if(! PASSWORD_REGEX.test(form.password.value)){
		raiseError(form.password, "Password should contain at least 1 for each lowercase, uppercase, number, special character. It should be length in 8 to 16")
		return false;
	}
	if(form.password.value != form['password-confirm'].value){
		raiseError(form['password-confirm'], "Password confirm mismatches.")
		return false;
	}
	if(form.name.value.length == 0){
		raiseError(form.name, "Name is mandatory");
		return false;
	}
	
	return true;
}
form.idCheck.addEventListener("click", function(e) {
	const id = form.id.value;
	if( ! ID_REGEX.test(id)){
		raiseError(form.id, 'ID should be longer than 3 characters, should only contain alphabet or number, starts with alphabet');
		return;
	}
	if(id){
		fetch('./id-check?id='+ encodeURIComponent(id))
		.then(response => response.json())
		.then(data => {
			if(data.exists){
				alert('ID already exists')
			} else {
				idConfirmed = true;
				alert('Confirmed.');
				form.password.focus();
			}
		})
		.catch(error => {
			alert('Error checking ID. Please try again.');
		})
	} else {
		alert('Fill ID first');
	}
})
form.signup.addEventListener("click", function(e) {
	if(validate()){
		form.submit();
	}
})
form.id.addEventListener("change", function(e){
	idConfirmed = false;
})