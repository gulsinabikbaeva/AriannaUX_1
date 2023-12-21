// const context = window.location.origin;
let categorySelect = document.querySelector("#category");
let subCategorySelect = document.querySelector("#subCategory");


categorySelect.addEventListener("change", () => {
    getSubCategory(categorySelect.value)
})


function getSubCategory(category) {
    let url = context + "/category/" + category + "/sub";
    let options = {method: "GET"};

    fetch(url, options).then(function (response) {
        return response.json();
    }).then(function (items) {

        let optionsHTML = "";
        for (let i = 0; i < items.length; i++) {
            optionsHTML += '<option value="' + items[i]["name"] + '">' + items[i]["name"] + '</option>';
        }

        subCategorySelect.innerHTML = optionsHTML;
        subCategorySelect.selectedIndex = 0;

    });
}
