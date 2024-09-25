<%@page import="kr.or.ddit.prod.vo.OptionVO"%>
<%@page import="kr.or.ddit.prod.vo.DeCateVO"%>
<%@page import="kr.or.ddit.prod.vo.ProdVO"%>
<%@page import="kr.or.ddit.prod.vo.MainCateVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 정보 입력 폼</title>
<style>
body {
    font-family: Arial, sans-serif;
    margin: 20px;
    background-color: #f7f7f7;
}

h1, h3, h2 {
    color: #333;
    text-align: center;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
    background-color: #fff;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

th, td {
    padding: 12px;
    border: 1px solid #ddd;
    text-align: left;
}

th {
    background-color: #f2f2f2;
    color: #333;
}

td {
    background-color: #fff;
}

tr:nth-child(even) {
    background-color: #f9f9f9;
}

tr:hover {
    background-color: #e6e6e6;
}

input[type="text"], select, input[type="file"] {
    width: 100%;
    padding: 8px;
    margin: 5px 0;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type="file"] {
    padding: 8px;
}

.button-container {
    text-align: center;
    margin-top: 20px;
}

input[type="submit"], input[type="reset"], input[type="button"], button {
    padding: 10px 20px;
    font-size: 16px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    margin-right: 10px;
    background-color: #d6cfcf;
    color: #333;
}

input[type="submit"]:hover, input[type="reset"]:hover, input[type="button"]:hover, button:hover {
    background-color: #b9a69b;
    color: #fff;
}

button {
    padding: 8px 15px;
    background-color: #d6cfcf;
    color: #333;
    border-radius: 4px;
    border: none;
    cursor: pointer;
}

button:hover {
    background-color: #b9a69b;
    color: white;
}

h3 {
    margin-bottom: 10px;
}

/* 옵션 추가 스타일 */
.optionRow {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
}

.optionRow input {
    flex: 2;
    margin-left: 10px;
    padding: 8px;
}

.removeOption {
    background-color: #b9a69b;
    color: white;
    border: none;
    cursor: pointer;
    padding: 5px 10px;
    border-radius: 5px;
}

/* 모달 스타일 */
.modal {
    display: none;
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgba(0, 0, 0, 0.4);
}

.modal-content {
    background-color: #fefefe;
    margin: 15% auto;
    padding: 20px;
    border: 1px solid #888;
    width: 40%;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.modal-content h2 {
    margin-top: 0;
    color: #333;
}

.close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
    cursor: pointer;
}

.close:hover,
.close:focus {
    color: black;
    text-decoration: none;
    cursor: pointer;
}

</style>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.7.1.js"></script>
<script type="text/javascript">
    $(function(){
        $("#btnList").on("click", function(){
            location.href="<%=request.getContextPath()%>/prod/prodList.do";
        });

        // 카테고리 연동
        $("#maincate").on("change", function() {
            var mainId = $(this).val(); 
            $.ajax({
                url: '<%=request.getContextPath()%>/prod/deCate.do',
                type: 'POST',
                data: { mainId: mainId },
                dataType: 'json',
                success: function(response) {
                    var $decateDropdown = $("#decate");
                    $decateDropdown.empty(); 
                    if (Array.isArray(response)) {
                        response.forEach(function(item) {
                            if (item.dcate_id && item.dcate_name) {
                                var $option = $('<option></option>')
                                    .text(item.dcate_name)
                                    .val(item.dcate_id);
                                $decateDropdown.append($option);
                            }
                        });
                    }
                },
                error: function(xhr, status, error) {
                    console.error('오류 발생:', status, error);
                }
            });
        });

        // 모달 제어
        $("#addMainCate").on("click", function() {
            $("#mainCateModal").show();
        });

        $("#addDeCate").on("click", function() {
            $("#deCateModal").show();
        });

        $(".close").on("click", function() {
            $(this).closest(".modal").hide();
        });
    });

    function addMainCategory() {
        var mainCateName = $("#mainCateName").val();
        $.ajax({
            url: '<%=request.getContextPath()%>/prod/mainCateInsert.do',
            type: 'POST',
            data: { mainCateName: mainCateName },
            success: function(response) {
                $("#mainCateModal").hide();
                alert("메인 카테고리가 추가되었습니다!");
                location.reload();
            },
            error: function(xhr, status, error) {
                console.error('오류 발생:', status, error);
            }
        });
    }

    function addDeCategory() {
        var deCateName = $("#deCateName").val();
        var mainCateId = $("#maincate").val();
        $.ajax({
            url: '<%=request.getContextPath()%>/prod/deCateInsert.do',
            type: 'POST',
            data: { deCateName: deCateName, mainCateId: mainCateId },
            success: function(response) {
                $("#deCateModal").hide();
                alert("세부 카테고리가 추가되었습니다!");
                location.reload();
            },
            error: function(xhr, status, error) {
                console.error('오류 발생:', status, error);
            }
        });
    }
</script>
</head>
<body>

<%
    List<MainCateVO> mcList = (List<MainCateVO>)request.getAttribute("mcList");
    List<OptionVO> OptionList = (List<OptionVO>)request.getAttribute("OptionList");
%>

<h2>상품 정보 입력</h2>

<form id="prodForm" method="post" enctype="multipart/form-data"
    action="<%=request.getContextPath()%>/prod/prodInsert.do">
<table>
<tbody>
    <tr>
        <td>제품명</td>
        <td><input type="text" name="prod_name" id="prod_name"></td>
    </tr>
    <tr>
        <td>제품내용</td>
        <td><input type="text" name="prod_content" id="prod_content"></td>
    </tr>
    <tr>
        <td>가격</td>
        <td><input type="text" name="prod_price" id="prod_price"></td>
    </tr>    
    <tr>
        <td>제품이미지</td>
        <td><input type="file" name="prod_image" multiple></td>
    </tr>
    <tr>
        <td>제품상태</td>
        <td><input type="text" name="prod_yn"></td>
    </tr>
    <tr>
        <td>할인률</td>
        <td><input type="text" name="prod_sale"></td>
    </tr>
    <tr>
        <td>카테고리 선택</td>
        <td>
            <select id="maincate">
                <option value="" selected>**카테고리 선택**</option>
                <%for(MainCateVO mcvo : mcList){%>
                    <option value="<%=mcvo.getCate_id()%>"><%=mcvo.getCate_name()%></option>
                <% } %>
            </select>
            <button type="button" id="addMainCate">추가</button>
            <br><br>
            
            <select id="decate" name="mainId">
                <option value="" selected>**세부카테고리 선택**</option>
            </select>
            <button type="button" id="addDeCate">추가</button>
            <br>
            
            <h3>옵션 추가하기</h3>
						<div id="optionContainer">
							<div class="optionRow">
								<label>옵션 컬러 입력</label>
								<input type="text" name="optionName[]" placeholder="베이직"> 
								<label>옵션 사이즈 입력</label> 
								<input type="text" name="optionValue[]" placeholder="90(cm) or 265(mm)">
								<label>옵션 재고량</label> 
								<input type="text" name="oprodQty[]" placeholder="100(개)">
								<button type="button" class="removeOption">삭제</button>
							</div>
						</div>
						<button type="button" id="addOption">옵션 추가</button> 
		</td>
    </tr>
    <tr>	
        <td colspan="2" style="text-align:center;">
            <input type="submit" value="저장"> 
            <input type="reset" value="취소">
            <input type="button" id="btnList" value="제품목록">
        </td>
    </tr>
</tbody>
</table>
</form>

<!-- Main Category Modal -->
<div id="mainCateModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h2>메인 카테고리 추가</h2>
        <input type="text" id="mainCateName" name="mainCateName" placeholder="카테고리 이름">
        <button onclick="addMainCategory()">추가</button>
    </div>
</div>

<!-- Detailed Category Modal -->
<div id="deCateModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h2>세부 카테고리 추가</h2>
        <input type="text" id="deCateName" name="deCateName" placeholder="세부 카테고리 이름">
        <button onclick="addDeCategory()">추가</button>
    </div>
</div>

</body>
</html>
