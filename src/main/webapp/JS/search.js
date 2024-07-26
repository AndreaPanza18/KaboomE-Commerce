document.getElementById('search-input').addEventListener('input', searchFunction);
document.getElementById('search-input').addEventListener('focus', showSuggestions);

function searchFunction() {
    const query = document.getElementById('search-input').value.toLowerCase();

    const searchDiv = document.querySelector('.search');
    if (query.length === 0) {
        document.getElementById('results').innerHTML = '';
        document.getElementById('results').style.display = 'none';
        searchDiv.classList.remove('active');
        return;
    }

    searchDiv.classList.add('active');

    const xhr = new XMLHttpRequest();
    xhr.open('GET', 'suggerimenti.txt', true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const data = xhr.responseText.split('\n');
            const results = data.filter(item => item.toLowerCase().includes(query));
            displayResults(results);
        }
    };
    xhr.send();
}

function showSuggestions() {
    const query = document.getElementById('search-input').value.toLowerCase();
    if (query.length > 0) {
        document.getElementById('results').style.display = 'block';
    }
}

function displayResults(results) {
    const resultsDiv = document.getElementById('results');
    resultsDiv.innerHTML = '';
    results.forEach(result => {
        const div = document.createElement('div');
        div.textContent = result;
        div.className = 'suggestion-item';
        div.addEventListener('click', function() {
            document.getElementById('search-input').value = result;
            resultsDiv.innerHTML = '';
            resultsDiv.style.display = 'none';
            document.querySelector('.search').classList.remove('active');
        });
        resultsDiv.appendChild(div);
    });
    if (results.length > 0) {
        resultsDiv.style.display = 'block';
    } else {
        resultsDiv.style.display = 'none';
    }
}

document.addEventListener('click', function(event) {
    const isClickInside = document.querySelector('.search').contains(event.target);
    if (!isClickInside) {
        document.getElementById('results').style.display = 'none';
        document.querySelector('.search').classList.remove('active');
    }
});
