window.onload = function(){
    loadReviews();
    document.getElementById('review-submit-button')
    .addEventListener('click', e=>{
        e.preventDefault();
        writeReview();
    })
}

const params = new URLSearchParams(window.location.search);
const prodId = params.get('id');

function loadReviews() {
	fetch('read-review-block?prodId=' + prodId)
    .then(response => {
		console.log("load started");
		return response.json();})
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
        document.getElementById('review-block').innerHTML = reviewsHTML;
    })
    .catch(error => {
        console.error('Error loading reviews: ', error);
    })
}

function writeReview() {
	const reviewArea = document.getElementById('write-content')
    const reviewData = {
        prodId: prodId,
        content: reviewArea.value,
    }

    fetch('upload-review', {
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