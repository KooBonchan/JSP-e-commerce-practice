window.onload = function(){
    loadReviews();
    document.getElementById('review-submit-button')
    .addEventListener('click', e=>{
        e.preventDefault();
        writeReview();
    })
}

function loadReviews() {
    fetch('read-review')
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
        document.getElementById('review-block').innerHTML = reviewsHTML;
    })
    .catch(error => {
        console.error('Error loading reviews: ', error);
    })
}

function writeReview() {
    const reviewData = {
        prodId: document.getElementById('prod-id').value,
        content: document.getElementById('write-content').value,
    }

    fetch('upload-review', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(reviewData),
    })
    .then(response => response.json())
    .then(data => {
        loadReviews();
    }).catch(error => {
        console.error('error uploading review: ', error);
    })
}