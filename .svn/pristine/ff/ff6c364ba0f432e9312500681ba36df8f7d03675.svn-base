<%@page import="kr.or.ddit.prod.vo.OptionVO"%>
<%@page import="kr.or.ddit.prod.vo.DeCateVO"%>
<%@page import="kr.or.ddit.prod.vo.ProdVO"%>
<%@page import="kr.or.ddit.prod.vo.MainCateVO"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Product</title>
<style type="text/css">
    table, tr, td { border:1px solid blue; border-collapse: collapse; }
    td { padding:5px;}
    .modal { display: none; position: fixed; z-index: 1; left: 0; top: 0; width: 100%; height: 100%; overflow: auto; background-color: rgb(0,0,0); background-color: rgba(0,0,0,0.4); }
    .modal-content { background-color: #fefefe; margin: 15% auto; padding: 20px; border: 1px solid #888; width: 80%; }
</style>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.7.1.js"></script>

<script type="text/javascript">
$(function(){
    $("#btnList").on("click", function(){
        location.href="<%=request.getContextPath()%>/prod/prodList.do";
    });

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
    ProdVO product = (ProdVO)request.getAttribute("product");
    List<MainCateVO> mcList = (List<MainCateVO>)request.getAttribute("mcList");
    List<OptionVO> optionList = (List<OptionVO>)request.getAttribute("OptionList");
    List<DeCateVO> dcList = (List<DeCateVO>)request.getAttribute("dcList");
%>

<h2>상품 정보 수정 폼</h2>

<form id="prodForm" method="post" enctype="multipart/form-data"
    action="<%=request.getContextPath()%>/prod/prodUpdate.do">
    <input type="hidden" name="prod_id" value="<%=product.getProd_id()%>">
    <table border="1">
    <tbody>
        <tr>
            <td>제품명</td>
            <td><input type="text" name="prod_name" id="prod_name" value="<%=product.getProd_name()%>"></td>
        </tr>
        <tr>
            <td>제품내용</td>
            <td><input type="text" name="prod_content" id="prod_content" value="<%=product.getProd_content()%>"></td>
        </tr>
        <tr>
            <td>가격</td>
            <td><input type="text" name="prod_price" id="prod_price" value="<%=product.getProd_price()%>"></td>
        </tr>    
        <tr>
            <td>제품이미지</td>
            <td><input type="file" name="prod_image" multiple></td>
        </tr>
        <tr>
            <td>제품상태</td>
            <td><input type="text" name="prod_yn" value="<%=product.getProd_yn()%>"></td>
        </tr>
        <tr>
            <td>할인률</td>
            <td><input type="text" name="prod_sale" value="<%=product.getProd_sale()%>"></td>
        </tr>
        <tr>
            <td>카테고리 선택</td>
            <td>
                <h3>카테고리 추가하기</h3>
                <select id="maincate">
                    <option value="" selected>**카테고리 선택**</option>
                    <% for(MainCateVO mcvo : mcList) { %>
                        <option value="<%=mcvo.getCate_id()%>" <% { %>selected<% } %>><%=mcvo.getCate_name()%></option>
                    <% } %>
                </select>
                <button type="button" id="addMainCate">추가</button>
                <br><br>
                
                <select id="decate" name="mainId">
                    <option value="" selected>**세부카테고리 선택**</option>
                    <% if (dcList != null) { 
                        for(DeCateVO dcvo : dcList) { %>
                            <option value="<%=dcvo.getDcate_id()%>" <% { %>selected<% } %>><%=dcvo.getDcate_name()%></option>
                        <% }
                    } %>
                </select>
                <button type="button" id="addDeCate">추가</button>
                <br>
                
                <h3>옵션 추가하기</h3>
                <div id="optionContainer">
                    <% for (OptionVO option : optionList) { %>
                    <div class="optionRow">
                        <label>옵션 컬러 입력</label>
                        <input type="text" name="optionName[]" placeholder="베이직"> 
                        <label>옵션 사이즈 입력</label> 
                        <input type="text" name="optionValue[]" placeholder="90(cm) or 265(mm)">
                        <label>옵션 재고량</label> 
                        <input type="text" name="oprodQty[]" placeholder="100(개)">
                        <button type="button" class="removeOption">삭제</button>
                    </div>
                    <% } %>
                </div>
                <button type="button" id="addOption">옵션 추가</button> 
                <script type="text/javascript">
                $(document).ready(function() {
                    $("#addOption").on("click", function() {
                        const optionRow = `
                            <div class="optionRow">
                                <label>옵션 컬러 입력</label>
                                <input type="text" name="optionName[]" placeholder="베이직">
                                <label>옵션 사이즈 입력</label>
                                <input type="text" name="optionValue[]" placeholder="90(cm) or 265(mm)">
                                <label>옵션 재고량</label>
                                <input type="text" name="oprodQty[]" placeholder="100(개)">
                                <button type="button" class="removeOption">삭제</button>
                            </div>`;
                        $("#optionContainer").append(optionRow);
                    });

                    $(document).on("click", ".removeOption", function() {
                        const optionRows = $(".optionRow");
                        if (optionRows.length > 1) {
                            $(this).closest(".optionRow").remove();
                        } else {
                            alert("최소 하나의 옵션이 필요합니다.");
                        }
                    });
                });
                </script>
            </td>
        </tr>
        <tr>    
            <td colspan="2" style="text-align:center;">
                <input type="submit" value="수정"> 
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
