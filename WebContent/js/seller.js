sizes = ''

function changeTab(tab) {
	sizes = ''
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
    sizes += document.getElementById('size-value').value.toUpperCase() + ';'
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
    document.getElementById('size-values').value = sizes
	
    reader.onload = function () {
        document.getElementById('submit-image').value = reader.result.substring(reader.result.indexOf(',') + 1)
        document.getElementById('add').submit()
    };

    reader.readAsDataURL(document.getElementById('image-selector').files[0]);

}

function modifyProduct(idProducto, nombreProducto, marca, talla, precio, desc, cantidad, imagen) {
	document.getElementById('viewProducts').style.display = 'none'
	let modify = document.getElementById('modify')
	modify.style.display = 'block'
	modify.elements["idProducto"].value = idProducto
	modify.elements["nombreProducto"].value = nombreProducto
	modify.elements["marca"].value = marca
	modify.elements["precio"].value = precio
	modify.elements["desc"].value = desc
	modify.elements["cantidad"].value = cantidad
	modify.elements["imagen"].value = imagen
	document.getElementById('product-image-modify').src = "data:image/png;base64," + imagen
	sizes = talla
	let sizes_ = talla.split(";")
	sizes_.pop()
	document.getElementById('sizes-modify').innerHTML = '<label>' +
		'<input type="text" id="size-value-modify" class="size-input" placeholder="Add size">' +
		'<input type="text" id="size-values-modify" value="" style="display: none;" name="talla">' +
		'<i class="fa fa-plus" onclick="addSizeMod()" style="position: relative; left: -30px; cursor: pointer;"></i>' +
	'</label>' +
	'<li style="margin: 0 8px 0 0 !important;"><span class="text-uppercase">Sizes:</span></li>'
	sizes_.forEach(s => {
		document.getElementById('sizes-modify').innerHTML += 
			'<li class="size"><a>' + s.toUpperCase() + '</a></li>'
	})
	
}

function addSizeMod() {
    sizes += document.getElementById('size-value-modify').value.toUpperCase() + ';'
    document.getElementById('sizes-modify').innerHTML += 
        '<li class="size"><a>' + document.getElementById('size-value-modify').value.toUpperCase() + '</a></li>'
}

function modifyProduct_() {
	var reader = new FileReader();
    document.getElementById('size-values-modify').value = sizes
	

    try {
        reader.readAsDataURL(document.getElementById('image-selector-modify').files[0]);
        reader.onload = function () {
            document.getElementById('submit-image-modify').value = reader.result.substring(reader.result.indexOf(',') + 1)
            document.getElementById('modify').submit()
        };
    }
    catch(e) {
    	document.getElementById('modify').submit()
    }

}

function viewProducts() {
	document.getElementById('viewProducts').style.display = 'block'
	document.getElementById('modify').style.display = 'none'
}
