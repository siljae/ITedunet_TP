function addToCart(action){
	document.addForm.action = action;
	document.addForm.submit();
	alert("상품이 장바구니에 추가되었습니다!")
}