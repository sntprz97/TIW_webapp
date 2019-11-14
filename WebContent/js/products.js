function getProductsPreview() {
	var req = new XMLHttpRequest();
	req.open('GET', 'getProductsPreview', true);
	req.onreadystatechange = function (aEvt) {
	  if (req.readyState == 4) {
	     if(req.status == 200) {
	    	 let res = JSON.parse(req.response)
	    	 showProducts(res)
	     }
	     else
	      alert("Error loading page");
	  }
	};
	req.send(null); 
}

function getProductsSearch(word) {
	console.log(word)
}

function showProducts(products) {
	let container = document.getElementById("products-container")
	 products.forEach(p => {
		 container.innerHTML += '<div class="col-md-4 col-sm-6 col-xs-6">' +
							'<div class="product product-single">' +
								'<div class="product-thumb">' +
									'<button class="main-btn quick-view"><i class="fa fa-search-plus"></i> Quick view</button>' +
									'<img src="data:image/png;base64, ' + p.imagen +'" alt="">' +
								'</div>' +
								'<div class="product-body">' +
									'<h3 class="product-price">' + p.precio + '&#8364</h3>' +
									'<h2 class="product-name"><a href="#">' + p.nombreProducto + '</a></h2>' +
									'<div class="product-btns">' +
										'<button class="main-btn icon-btn"><i class="fa fa-heart"></i></button>' +
										'<button class="main-btn icon-btn"><i class="fa fa-exchange"></i></button>' +
										'<button class="primary-btn add-to-cart"><i class="fa fa-shopping-cart"></i> Add to Cart</button>' +
									'</div>' +
								'</div>' +
							'</div>' +
						'</div>'
	 })
}