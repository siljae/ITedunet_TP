<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
<link rel = "stylesheet" href="<c:url value = "/resources/css/productadd.css" />">
    <script src="https://kit.fontawesome.com/014e61e9c4.js" crossorigin="anonymous"></script>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="container1">
        <div class="bbubbu">
            <nav class="cos_nav">
                <ul>
                    <li class = "com_name">
                        SHOP
                    </li>
                </ul>
            </nav>
        </div>
    </div>
    <div class="lounge">
        <div class="midbox">
            <div class="sidecate">
                <div class="sidehea">
                    <span class="catetitle">CATEGORY</span>
                </div>
                <hr class="sidehr">
                <div class="listnamebox">
                    <span class="listname">DOG</span>
                </div>
                <div class="listtitle">
                    <ul class="list">
                        <li class="catelist">
                            <a class="cate" href=""><img src="./img/foot-removebg-preview.png" alt=""><span class="text">사료</span><img src="./img/foot-removebg-preview.png" alt=""></a>
                        </li>
                        <li class="catelist">
                            <a class="cate" href=""><img src="./img/foot-removebg-preview.png" alt=""><span class="text2">간식</span><img src="./img/foot-removebg-preview.png" alt=""></a>
                        </li>
                        <li class="catelist">
                            <a class="cate"href=""><img src="./img/foot-removebg-preview.png" alt=""><span class="text3">용품</span><img src="./img/foot-removebg-preview.png" alt=""></a>
                        </li>
                        <li class="catelist">
                            <a class="cate"href=""><img src="./img/foot-removebg-preview.png" alt=""><span class="text4">장난감</span><img src="./img/foot-removebg-preview.png" alt=""></a>
                        </li>
                    </ul>
                </div>
                <div class="listnamebox">
                    <span class="listname">CAT</span>
                </div>
                <div class="listtitle">
                    <ul class="list">
                        <li class="catelist">
                            <a class="cate" href=""><img src="./img/foot-removebg-preview.png" alt=""><span class="text">사료</span><img src="./img/foot-removebg-preview.png" alt=""></a>
                        </li>
                        <li class="catelist">
                            <a class="cate" href=""><img src="./img/foot-removebg-preview.png" alt=""><span class="text">간식</span><img src="./img/foot-removebg-preview.png" alt=""></a>
                        </li>
                        <li class="catelist">
                            <a class="cate"href=""><img src="./img/foot-removebg-preview.png" alt=""><span class="text">용품</span><img src="./img/foot-removebg-preview.png" alt=""></a>
                        </li>
                        <li class="catelist">
                            <a class="cate"href=""><img src="./img/foot-removebg-preview.png" alt=""><span class="text">장난감</span><img src="./img/foot-removebg-preview.png" alt=""></a>
                        </li>
                    </ul>
                </div>
                <div class="listtitle">
                    <ul class="list">
                        <div class="listnamebox">
                            <span class="listname">My Page</span>
                        </div>
                        <li class="catelist">
                            <a class="cate" href=""><span class="text">장바구니</span></a>
                        </li>
                        <li class="catelist">
                            <a class="cate" href=""><span class="text">주문목록</span></a>
                        </li>
                    </ul>
                </div>
                <div class="listtitle">
                    <ul class="list">
                        <div class="listnamebox">
                            <span class="listname">고객센터</span>
                        </div>
                        <li class="catelist">
                            <a class="cate" href=""><span class="text">QnA</span></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="midlounge">
            <div class="writebox">
                <div id="bodertitlebox">
                    <div class="title" >
                        <h2 class="tltelcss">상품등록</h2>
                    </div>
                    <hr class="hrline">
                    <div id="write_area">
                        <form:form modelAttribute="NewProduct" enctype="multipart/form-data" action="./add${_csrf.parameterName}=${_csrf_token}" class="from-hori">
                            <div id="in_title">
                                <p class="titletxt">상품명</p>
                                <select name="conoption" id="conoption">  
                                      <!-- 나중에 선택은 유효성 검사 통해서 잘못선택했다고 경고창 띄워줌 -->
                                    <option id="opdefult"value="none">카테고리</option>
                                    <optgroup label="DOG">
                                        <option value="dogfood">사료</option>
                                        <option value="dogsnake">간식</option>
                                        <option value="dogliving">간식</option>
                                        <option value="dogtoy">장난감</option>
                                    </optgroup>                             
                                    <optgroup label="CAT">
                                        <option value="catfood">사료</option>
                                        <option value="catsnake">간식</option>
                                        <option value="catliving">간식</option>
                                        <option value="cattoy">장난감</option>
                                    </optgroup> 
                                </select>
                                <textarea name="title" id="utitle" rows="1" cols="55" maxlength="100" required></textarea>
                            </div>
                            <hr class="hrline">
                            <div id="in_content">
                                <p class = "contenttxt">상품타이틀멘트</p>
                                <textarea name="timent" id="utiment" required></textarea>
                            </div>
                            <hr class="hrline">
                            <div id="in_content">
                                <p class = "contenttxt">상품간단설명</p>
                                <textarea name="content" id="ucontent" required></textarea>
                            </div>
                            <hr class="hrline">
                            <div class="upinfo">
                                <div class="pricebox">
                                    <p class="pricetxt">판매가</p>
                                    <input type="text" class="upload-price" name="price" >
                                    <p class="pricetxt">원</p>
                                </div>
                                <div class="quantitybox">
                                    <p class="quantitytxt">재고수량</p>
                                    <input type="text" class="upload-quantity" name="quantity" >
                                    <p class="quantitytxt">개</p>
                                </div>
                                <p class="waringtxt">※ 숫자로만 입력하세요 !</p>
                            </div>
                            <hr class="hrline">
                            <div class="filebox">
                                <p class="filetitle">상품 정보 이미지</p>
                                <input class="upload-name" value="첨부파일" placeholder="첨부파일"/>
                                <label for="file"> 파일찾기</label>
                                <input type="file" id="file">
                            </div>
                            <hr class="hrline">
                            <div class="filebox">
                                <p class="filetitle">상품 대표 이미지</p>
                                <input class="upload-name" value="첨부파일" placeholder="첨부파일"/>
                                <label for="file"> 파일찾기</label>
                                <input type="file" id="file">
                            </div>
                            <hr class="hrline">
                            <div class="bt_se">
                                <button class="subutton" type="submit">글 작성</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>