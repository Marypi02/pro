$(document).ready(function() {
    // Evento di input sulla barra di ricerca
    $('#searchInput').on('input', function() {
        var query = $(this).val();
        if (query !== '') {
            searchProducts(query);
        } else {
            $('#searchResults').empty();
        }
    });

    // Evento di click sul pulsante di ricerca
    $('#searchButton').on('click', function() {
        var query = $('#searchInput').val();
        if (query !== '') {
            searchProducts(query);
        } else {
            $('#searchResults').empty();
        }
    });
});

function searchProducts(query) {
    $.ajax({
        url: 'SearchServlet',
        type: 'GET',
        data: { query: query },
        success: function(response) {
            displaySearchResults(response);
        },
        error: function(xhr, status, error) {
            console.error('Error:', status, error);
        }
    });
}

function displaySearchResults(results) {
    var searchResultsDiv = $('#searchResults');
    searchResultsDiv.empty();

    if (results.length === 0) {
        searchResultsDiv.append('<p>Nessun risultato trovato.</p>');
    } else {
        for (var i = 0; i < results.length; i++) {
            var product = results[i];
            var productLink = '<a href="product?id=' + product.code + '">' + product.name + '</a>';
            searchResultsDiv.append('<p>' + productLink + '</p>');
        }
    }
}
