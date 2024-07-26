document.addEventListener('DOMContentLoaded', function () {
    const searchInput = document.querySelector('.search-input');
    const suggestionsContainer = document.querySelector('.suggestions-container');

    searchInput.addEventListener('input', function () {
        const query = searchInput.value.trim();

        if (query.length > 0) {
            fetch('suggestions.txt')
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Errore nella richiesta dei suggerimenti');
                    }
                    return response.json();
                })
                .then(data => {
                    const suggestions = data.filter(item => item.toLowerCase().includes(query.toLowerCase()));

                    if (suggestions.length > 0) {
                        suggestionsContainer.innerHTML = suggestions.map(item => `
                            <div class="suggestion-item" data-value="${item}">
                                ${item}
                            </div>
                        `).join('');
                    } else {
                        suggestionsContainer.innerHTML = '<div class="no-suggestions">Nessun suggerimento trovato</div>';
                    }
                })
                .catch(error => {
                    console.error('Errore nella richiesta dei suggerimenti:', error);
                });
        } else {
            suggestionsContainer.innerHTML = '';
        }
    });

    suggestionsContainer.addEventListener('click', function (event) {
        if (event.target.classList.contains('suggestion-item')) {
            searchInput.value = event.target.getAttribute('data-value');
            suggestionsContainer.innerHTML = '';
        }
    });
});
