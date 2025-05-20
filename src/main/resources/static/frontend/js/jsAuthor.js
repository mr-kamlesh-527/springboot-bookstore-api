document.getElementById('bookForm').addEventListener('submit', function(e) {
    e.preventDefault();

    const authorId = document.getElementById('authorId').value;
    const title = document.getElementById('title').value;
    const isbn = document.getElementById('isbn').value;
    const price = parseFloat(document.getElementById('price').value);

    // Construct book object
    const bookData = {
        authorId: authorId,
        title: title,
        isbn: isbn,
        price: price
    };

    fetch('http://localhost:8080/api/books', {  // change URL to your backend
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            // add auth token here if needed
        },
        body: JSON.stringify(bookData)
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            return response.json().then(err => Promise.reject(err));
        }
    })
    .then(data => {
        document.getElementById('message').textContent = 'Book added successfully!';
        this.reset();
    })
    .catch(error => {
        document.getElementById('message').textContent = 'Error: ' + (error.message || 'Could not add book');
    });
});
