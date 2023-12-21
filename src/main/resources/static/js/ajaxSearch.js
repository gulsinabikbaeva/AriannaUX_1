const context = window.location.origin;

document.querySelector("#search-input").addEventListener("keyup", function () {
    let textValue = document.querySelector("#search-input").value;
    if (textValue.length > 2)
        search(textValue);
})

function search(value) {
    let url = context + "/item/search?q=" + value;
    let options = {method: "GET"};

    fetch(url, options).then(function (response) {
        return response.json();
    }).then(function (items) {
        let container = document.querySelector("#mainContainerID");

        let articles = '';
        for (let i = 0; i < items.length; i++) {
            let date = new Date(items[i].date);
            let dateFormatted = date.getDate() + '.' + (date.getMonth() + 1) + "." + date.getFullYear();
            let type = items[i].type === "Domanda" ? "Compra" : "Vendo";

            articles += '<article class="col-md-3 col-lg-2">\n' +
                '                <div class="card mb-2 shadow-sm">\n' +
                '                    <a href="' + context + 'item/' + items[i].id + '"><img class="bd-placeholder-img card-img-top" src="' + context + '/item/' + items[i].id + '/image" class="img-fluid""/></a>\n' +
                '                    <div class="card-body">\n' +
                '                        <p style="color:grey"><span style="color:#600">' + type + '</span> | <span class="category">' + items[i].category + '</span> | <span>' + dateFormatted + '</span> by <a href="#">' + items[i].author + '</a></p>\n' +
                '                        <p><span>' + items[i].title + '</span></p>\n' +
                '                        <div class="d-flex justify-content-between align-items-center">\n' +
                '                            <div class="btn-group">\n' +
                '                                <a class="btn btn-sm btn-outline-secondary" href="' + context + '/item/' + items[i].id + '">View</a>\n' +
                '                                \n' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                        </div>\n' +
                '                    </div>\n' +
                '            </article>\n';
        }

        if (items.length === 0) {
            articles = '<article class="col-md-4"><p>Nessun annuncio <tror></tror>to</p></article>';
        }

        container.innerHTML = '<h2>Risultati ricerca: ' + value + ' <a class="btn btn-sm btn-danger" href="' + window.location.href + '">chiudi</a></h2>' +
            '<section class="row">\n' +
            '        \n' +
            articles +
            '            \n' +
            '        \n' +
            '    </section>';

    });
}
