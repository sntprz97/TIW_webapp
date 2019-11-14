function changeTab(tab) {
    let tabs = document.getElementsByClassName('action')
    let buttons = document.getElementsByClassName('action-btn')
    for (let i = 0; i < tabs.length; i++) {
        tabs[i].style.display = 'none'
        buttons[i].classList.remove('active')
    } 
    document.getElementById(tab).style.display = 'block'
    document.getElementById(tab + '-button').classList.add('active')
}

function addSize() {
    document.getElementById('size-values').value += document.getElementById('size-value').value.toUpperCase() + ';'
    document.getElementById('sizes').innerHTML += 
        '<li class="size"><a>' + document.getElementById('size-value').value.toUpperCase() + '</a></li>'
}

document.getElementById('image-selector').onchange = function () {
    var reader = new FileReader();

    reader.onload = function (e) {
        document.getElementById("product-image").src = e.target.result;
    };

    reader.readAsDataURL(this.files[0]);
};

function addProduct() {
	var reader = new FileReader();
	
    reader.onload = function () {
        document.getElementById('submit-image').value = reader.result.replace('data:image/jpeg;base64,','')
        document.getElementById('add').submit()
    };

    reader.readAsDataURL(document.getElementById('image-selector').files[0]);


}