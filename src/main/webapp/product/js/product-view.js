window.onload = function(){
    loadReviews();
    document.getElementById('review-submit-button')
    .addEventListener('click', e=>{
        e.preventDefault();
        writeReview();
    });
    document.getElementById('cart-submit-button')
    .addEventListener('click', e=>{
		e.preventDefault();
		addToCart();
	})
}

const params = new URLSearchParams(window.location.search);
const prodId = params.get('id');
const cart = document.getElementById('cart');
const count = document.getElementById('prod-count');


const placeholder = "<li>No reviews yet. Would you leave my first comment?</li>";
function loadReviews() {
	
	fetch('/product/read-review-block?prodId=' + prodId)
    .then(response => response.json())
    .then(data => {
        let reviewsHTML = '';
        data.forEach(review => {
            reviewsHTML += `
                <li>
                <div class="review">
                    <span class="writer">${review.writer}</span>
                    <span class="content">${review.content}</span>
                </div>
                </li>
            `;
        });
        if(reviewsHTML.length == 0) reviewsHTML = placeholder;
        document.getElementById('review-block').innerHTML = reviewsHTML;
    })
    .catch(error => {
        console.error('Error loading reviews: ', error);
    })
}

function writeReview() {
	const reviewArea = document.getElementById('write-content')
	if(reviewArea.value.trim().length == 0) return;
    const reviewData = {
        prodId: prodId,
        content: reviewArea.value,
    }

    fetch('/product/upload-review', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(reviewData),
    })
    .then(ignored => loadReviews())
    .then(ignored=>{
		reviewArea.value = '';
	})
    .catch(error => {
        console.error('error uploading review: ', error);
    })
}

function addToCart(){
	if(count.value == 0) return;
    const item = {
        prodId: prodId,
        count: count.value,
    }
    fetch('/product/add-to-cart', {
		method: 'POST',
		headers: {
			'Content-type': 'application/json',
		},
		body: JSON.stringify(item),
	})
	.then(response=>response.json())
	.then(data=>{
		if(data.success == 'true'){
			alert("Successfully added to Cart");
			count.value=0;
			
			updateCart();
		} else {
			alert("Failed to add to Cart. Try again later.");
		}
	})
	.catch(e=>console.log(item, e))
}

function updateCart(){
	fetch('/product/cart-count')
	.then(response => response.json())
	.then(data => {
		document.getElementById('cart').innerHTML = data.count;
	})
	.catch(e=>console.log(e))
}