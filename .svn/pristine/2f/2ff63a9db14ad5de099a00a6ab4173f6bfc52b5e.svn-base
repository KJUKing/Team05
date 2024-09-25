<%@page import="com.google.gson.Gson"%>
<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery-3.7.1.js"></script>
		<script src="${pageContext.request.contextPath}/js/qna.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.serializejson.min.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/qnaStyle.css">
		
	
		<meta charset="UTF-8">
		<title>QnA</title>
		
		<style>

		</style>

		
<%
	// 로그인 상태 확인 start
	MemberVO vo = (MemberVO)session.getAttribute("loggedInMember"); // LoginContgroller.java에서 session으로 넘어옴.
	String loggedInMember = null;
	Gson gson = new Gson();
	if(vo != null) loggedInMember = gson.toJson(vo);
	
	// 관리자 = 댓글쓰기, 댓글수정, 댓글삭제, 글삭제
	// 일반 회원 = 글쓰기, 자기글수정
	// loggedInMember 저거를 사용해서, 로그인 한 사람의 id와 비번, 그리고 mem_role 을 가져와서, mem_role이 관리자인 사람일때를 분리해야함
	// int memRole = vo.getMemRole(); // memRole = 1 = 관리자 // memRole = 0 = 회원
	

	
	
%>
		<script>
			/* 보관함
			$(document).ready(function() { });
			$('#id').on('click', function() { });
			$('.class').on('click', function() { });
			$('#testbtn1').on('click', function() { });
			*/
			
			// 변수 설정
			uvo = <%= loggedInMember %>;

			imdId = '';
			
			// 테스트용 시작
			<% String loginId = "p002"; %>;
			loginId = "p002";
			// uvo = "p002";
			// 테스트용 끝
			var sendProdId;
			reply = {};
			
			currentPage = 1;
			mypath = '<%=request.getContextPath()%>'
			$proddropdown = $('.prod-dropdown');
			
			$(document).ready(function() {
				
				// 리스트 출력 기능 시작
				// 화면에 qna 리스트 출력하기
			    $.qnaList();
				
				// 다음버튼 클릭 이벤트
				$(document).on('click', '#next', function(){
					currentPage = parseInt($('.pageno').last().text()) + 1;
					$.qnaList();
				});
				
				// 이전버튼 클릭 이벤트
				$(document).on('click', '#prev', function(){
					currentPage = $('.pageno').first().text() - 1;
					$.qnaList();
				});
				
				// 페이지 번호 클릭 이벤트
				$(document).on('click', '.pageno', function(){
					currentPage = $(this).text();
					$.qnaList();
				});
				
				// 검색어 입력 후 검색버튼 클릭 이벤트
				$(document).on('click', '#search', function(){
					currentPage = 1;
					$.qnaList();
				});
				// 리스트 출력 기능 끝
				console.log('글쓰기 버튼 전 uvo 확인 : ' + uvo.memId);
				if(uvo.memId!=null && uvo.memId!='admin') {
					$('#qnaInsert').css('display', 'block');
				};

			
			

				// 문의글쓰기 기능 모달창 시작
				//$(document).ready(function() {
					// qna 작성용 모달창 열기 시작
				$(document).on('click', '#hiddenbutton', function() {
						console.log('글쓰기 진입');
						
						if(uvo == null) {
							alert("로그인 하세요");
							return;
						}
						// 모달 이름칸에 이름 넣기
						// $('#writer').val(uvo.memId);
						$('#writer').val(uvo.memId); // 임시변수
						$('#prod').val('test'); // 임시변수, 선택한 아이템 정보 불러와서 넣기.
					
						// 모달 이름 수정 불가 설정
						$('#writer').prop('readonly', true);
						$('#prod').prop('readonly', true);
						
						// 모달 보이게 하기
						$('#qnaModal').modal('show');
						$('#qnaModal #subject').val("");
					});
					// qna 작성용 모달창 열기 끝
					
					// 완료 버튼 클릭 시작
					$('#send').on('click', function(){
						console.log('글쓰기 완료 버튼 클릭');
						// 입력한 모든 내용 가져오기
						fdata3 = $('#qnaForm').serializeJSON();
						console.log(fdata3);
						
						// 글쓰기 함수 호출
						$.qnaInsertPost();
						
						// 모달창 닫기
						$('#qnaModal').modal('hide');
						
						// 모달창 내용 지우기
						$('#qnaModal .txt').val("");
					});
					// 완료 버튼 클릭 끝
				//})
				// 문의글쓰기 기능 모달창 끝
				
				
				
				
				
				// 자
				// 글쓰기 할때는 상품명을 찾게 해줘야하고
				// 수정 할때는 상품 디테일을 자동으로 가져와야 하고
				// 상품페이지에서 글쓰기 할때는 모달창만 띄워야 한다
				
				
				
				
				// 모달 테스트
				// 모달 테스트
				// 모달 테스트
				// 모달 테스트
				// 모달 테스트
				// 모달 테스트
				// 모달 테스트
				// 모달 테스트
				// 모달 테스트
				// 모달 테스트
				// 모달 테스트
				$(document).ready(function() {
					let timer;
				    products = [];
				    textboxFocused = false;

					// qna 작성용 모달창 열기 시작
					// $('#qnaInsert').on('click', function(){
					$(document).on('click', '#qnaInsert', function() {
						if(uvo == null) {
							alert("로그인 하세요");
							return;
						}
						
						console.log('상품 사진 선택 출력 기능');
						$('#writer2').val('');
						a = `<input type="button">`;
						$('#writer2').append(a);
						
						// db에서 상품 이름 다 가져오기
						$.getAllProdName();
						
						/*
						if(uvo == null) {
							alert("로그인 하세요");
							return;
						}
						*/
						
						// 모달 이름칸에 이름 넣기
						$('#writer2').val(uvo.memId);
						$('#writer2').prop('readonly', true);

						$('#prod2').val('');

						
						// 모달 보이게 하기
						$('#qnaModal2').modal('show');
						$('#qnaModal2 #subject').val("");
					})
					// qna 작성용 모달창 열기 끝
					
					
					
					
					// 엔터 누르기 감지
					/*
				    $('#prod2').on(function(event){
				        if(event.keyCode === 13) {
				            let query = $(this).val();
				            alert("읭");
				            console.log('Enter 키 눌림, 입력값:', query);
				            // 여기서 AJAX 요청을 보내거나 원하는 동작을 수행하면 돼.
				        }
				    })
				    */
				    firstClick = true;
					itemIndex = -1;
				    // 상품명 입력창 클릭시 리스트창 펼치기
				    $('#prod2').on('focus', function() {
				        if (!textboxFocused) {
				        	textboxFocused = true;
				            console.log('entered');
						    firstClick = true;
							itemIndex = 0;
							started = false;
							$proddropdown.children('div').removeClass('selected');
				        }
				        $('.prod-dropdown-cover').css('display', 'block');
				    })
				    
				    // 상품명 입력창 벗어나면 리스트창 접기
				    $('#prod2').on('blur', function() {
				    	textboxFocused = false;
				    	console.log('left');
				    	$('.prod-dropdown-cover').css('display', 'none');
				    	firstClick = true;
				    	itemIndex = 0;
				    	$proddropdown.children('div').removeClass('selected');
				    	started = false;
				        // 여기에 커서가 벗어났을 때의 동작을 추가
				    });
				    
					// 입력시
					$('#prod2').on('input', function() {
				        clearTimeout(timer); // 이전 타이머를 취소한다
				        myinput = $(this).val().toLowerCase();
				        $proddropdown = $('.prod-dropdown');
				        $proddropdown.empty();
				        
				        // dropdown 창에 상품명 5개까지 출력하기
				        const maxItems = 6;
				        limit = maxItems - 1;
				        let count = 0;
				        products.forEach(function(product) {
				            // 상품명을 공백 제거하고 소문자로 변환
				            // let productName = product.prod_name.replace(/\s+/g, '').toLowerCase();
				            let productName = product.prod_name.replace(/[\s\[\]\/]+/g, '').toLowerCase();
				            // 입력값을 공백 제거하고 소문자로 변환
				            // let inputName = myinput.replace(/\s+/g, '').toLowerCase();
				            let inputName = myinput.replace(/[\s\[\]\/]+/g, '').toLowerCase();

				            // 입력값을 정규 표현식 패턴으로 변환
				            const pattern = inputName.split('').join('.*'); // 각 문자 사이에 .* 추가
				            const regex = new RegExp(pattern); // 정규 표현식 객체 생성

				            // 상품명과 패턴 비교
				            if (regex.test(productName)) {
				                if (count < maxItems) {
				                    $proddropdown.append('<div>' + product.prod_name + '</div>');
				                    count++;
				                }
				                console.log('product 출력 : ' + product);
				            }
				        });
				        
				        let isHandlingKey = false; // 상태 변수를 추가
				        started = false;
				        $(document).on('keydown', function(e) {
				            if (isHandlingKey) return; // 이미 처리 중이면 반환
				            isHandlingKey = true; // 처리 시작
				        	const itemList = $proddropdown.children('div');
				            if (itemList.length === 0) {
				                isHandlingKey = false; // 처리 끝
				                return;
				            }
				        	if(e.key === 'ArrowDown') {
				        		e.preventDefault();
				        		if(started==false) {
				        			itemIndex = 0;
				        			updateHighlight();
					        		started = true;
				        			itemIndex = (itemIndex + 1);
				        		} else {
				        			updateHighlight();
				        			itemIndex++;
				        		}
				        		if(itemIndex >= itemList.length) {
				        			itemIndex = 0;
				        		}
				        	} else if(e.key === 'ArrowUp') {
				        		if(started==false) {
				        			updateHighlight();
					        		started = true;
				        		}
				        		e.preventDefault();
				        		updateHighlight();
				        		itemIndex = (itemIndex - 1);
				        		if(itemIndex < 0) {
				        			itemIndex = (itemList.length - 1);
				        		}
				        		updateHighlight();
				        	} else if (e.key === 'Enter') {
				                e.preventDefault();
				                selectedItem = itemList.eq(itemIndex).text(); // 선택된 텍스트 가져오기
				                $('#prod2').val(selectedItem); // 입력 필드에 텍스트 넣기
				                myinput = $('#prod2').val();
				                // 다음 입력 필드로 커서 이동
				                $('.prod-dropdown-cover').css('display', 'none');
				                $('#subject2').focus();
				                getImage();
				        	} else if (e.key === 'Backspace') {
				        		$proddropdown.children('div').removeClass('selected');
				        		itemIndex = 0;
				        	}
				        	isHandlingKey = false; // 처리 끝
				        });
				        function updateHighlight() {
			                $proddropdown.children('div').removeClass('selected');
			                $proddropdown.children('div').eq(itemIndex).addClass('selected');
			                selectedItem = $proddropdown.children('div').eq(itemIndex).text();
			                $('#prod2').val(selectedItem);
			            }

						// 입력 완료하면 1초 후에 DB에 연결해서 이미지 가져오기
						function getImage() {
						timer = setTimeout(function() {
							if(myinput !== '') {
								console.log('입력한 상품명 1초뒤 출력한것 : ' + myinput);
								
					            // 상품명에 맞는 prod_id 찾기
					            var prodId;
					            products.forEach(function(product) {
					                if (product.prod_name.replace(/\s+/g, '').toLowerCase() === myinput.replace(/\s+/g, '').toLowerCase()) {
					                    prodId = product.prod_id; // 일치하는 경우 prod_id 저장
					                    console.log('product.prod_id 반복 출력 : ' + product.prod_id);
					                    // console.log('prodId 반복 출력 : ' + prodId);
					                }
					            });
					            console.log('사진 찾으러 가기 전 prodId 확인 : ' + prodId);
					            sendProdId = prodId;
					            $.ajax({
					            	url: `<%= request.getContextPath() %>/getImageTargetId.do`,
					            	type: 'get',
					            	data: {target : prodId},
					            	success: function(res1) { // 이게 사진 파일
					            		sessionStorage.setItem('image', res1);
					            		console.log('res1 가져온거 성공, 이거는 imageId : '+ res1)
					            		window.finalImageId = res1;
					            		console.log(finalImageId + '이거 없으면 안들어가지는데');
										$.ajax({
											url: `<%= request.getContextPath() %>/images/prodImageView.do`,
											type: 'get',
											data: {"imgId" : finalImageId},
											success: function(res) {
												console.log(res);
												console.log(res + '가 없는거 같은데..');
												console.log('img 가져오기 성공하면 띄우기 ' + res);
												$('#qnaProdPic2').attr('src', `<%= request.getContextPath() %>/images/prodImageView.do?imgId=` + finalImageId);
											},
											error: function(xhr) {
												console.log('없는 상품 : ' + xhr.status);
												// 이미지가 없을 때 기본 이미지 설정
												$('#qnaProdPic2').attr('src', `<%= request.getContextPath() %>/images/empty-logo.png`)
												.attr('alt', '없는 상품')
												.css('background-color', 'white');
											}
										}); // 사진 가져오기 ajax 끝
					            	},
					            	error: function(xhr) {
					            		console.log('prodId 보내서 targetId 찾아서 imageid 가져오는거 실패한 것' + xhr.status);
					            	}
					            });
							}
						}, 1000); // 1초 후 실행
						};
					});
					
					
					// 완료 버튼 클릭 시작
					$('#send2').on('click', function(){
						console.log('글쓰기 완료 버튼 클릭');
						// 입력한 모든 내용 가져오기
						$('#prod2').val(sendProdId);
						fdata3 = $('#qnaForm2').serializeJSON();
						console.log(fdata3);
						
						// 글쓰기 함수 호출
						$.qnaInsertPost();
						
						// 모달창 닫기
						$('#qnaModal2').modal('hide');
						
						// 모달창 내용 지우기
						$('#qnaModal2 .txt').val("");
						$('#qnaProdPic2').attr('src', `<%= request.getContextPath() %>/images/empty-logo.png`);
					});
					// 완료 버튼 클릭 끝
				});
				// 모달 테스트
				// 모달 테스트
				// 모달 테스트
				// 모달 테스트
				// 모달 테스트
				// 모달 테스트
				// 모달 테스트
				// 모달 테스트
				// 모달 테스트
				// 모달 테스트
				// 모달 테스트
				
				
				
				// 닫기 버튼 누르면 사진 지우기
				$('.modal-footer button[type="button"]').on('click', function() {
				    $('#qnaProdPic2').attr('src', `<%= request.getContextPath() %>/images/empty-logo.png`);
				});
				
				
				
	
				
				
				// action 기능 시작
				// $(document).on('click', '.action', function(){
				$(document).on('click', '.action', function(){
					target = $(this);
					vname = $(this).attr('name');
					vidx = $(this).attr('idx');
					vstat = $(this).attr('status');
					console.log(target);
					console.log(vname);
					console.log('첫번재 vidx는 '+vidx);
					
					// 글 수정하기 기능 시작
					if (vname == "modify") {
						// 원글의 내용을 가져온다
						// 수정버튼을 기준으로 공통조상 찾기
						vcard = $(this).parents('.card');
						vqnaid = $(vcard).find('.btn.action').attr('idx');
						vtitle = $(vcard).find('a#title').html().replace(/<span.*?<\/span>/g, '').trim();
						vname = $(vcard).find('.wr').text().trim();
						vcont = $(vcard).find('.p3').html().trim(); // <br> 태그가 포함
						vcont = vcont.replaceAll(/<br>/g, "");
						
						//console.log(vcard);
						//console.log(vtitle);
						//console.log(vname);
						console.log(vcont);
						//console.log(vqnaid);
						
						
						// 모달창에 출력한다
						pic = sessionStorage.getItem('image');
						$('#qnaProdPic').attr('src', pic);
						$('#uwriter').val(vname).prop('readonly', true);
						$('#usubject').val(vtitle);
						$('#uprod').val('test').prop('readonly', true); // 이것도 연결해야함
						$('#ucontent').val(vcont);
						$('#unum').val(vqnaid);
						
						// 모달창 실행
						$('#uModal').modal('show');
						
					// 글 수정하기 기능 끝
					// 글 삭제하기 시작
					} else if (vname == "delete") { // 글 삭제하기
						if(confirm("정말로 삭제하시겠습니까?")) {
							// 함수 호출. board.js에 있는거.
							$.qnaDeletePost();
						} else {
							return;
						}
					
					// 글 삭제하기 끝
					// 댓글 달기 시작 (수정기능 동일)
					} else if (vname == "reply") {
						// 문의글 댓글 작성은 관리자만 가능
						if(uvo != 'admin') {
							alert("잘못된 접근");
							return;
						}
						
						// 입력한 댓글내용 가져오기
						vcont = $(this).prev().val().trim();
						
						// 저장할 데이터 수집 renum bonum cont
						// 위에서 만들어둔 빈 스크립트 객체 reply, 동적으로 속성 추가하기
						reply.qna_no = vidx; // 게시글 번호
						// reply.name = uvo.mem_name; // 작성자명
						reply.qna_answer = vcont; // 내용
						
						// 전송 - board.js의 함수를 호출
						$.qnaInsertReply();
						$(this).prev().val("");
						
						// 댓글 리스트 가져오기 // 비동기니까 데이터도 안가져왔는데 출력부터 해버릴려고 할 수 있음
						// 그래서 ajax에서 해야함
						
					// 댓글 달기 끝
					// 문의글 신고하기 시작
					} else if (vname == "report") {
						if (confirm("정말로 신고하시겠습니까?")) {
							reportPostNum = $(this).parents('.card').find('#title').attr("idx");
							console.log(idx);
							$.reportPost();
						} else {
							return;
						}
						/*
						// 입력한 모든 값을 가져온다
						udata = $('#uform').serializeJSON();
						console.log(udata);
						
						// 모달창에 입력된 내용 지우기
						$('#uform .txt').val("");
						
						// 모달창 닫기
						$('#uModal').modal('hide');
						
						// 서버로 전송한다 - js함수 호출 - 동기방식은 가져와야하고, 비동기는 안가져와도 된다.. 흐음.. 동기 비동기..
						*/
						
					// 문의글 신고하기 끝
					// 댓글 출력하기 시작
					} else if (vname == "title") {
						// 함수 호출
						$.qnaReplyList();
					};
					// 댓글 출력하기 끝
				});
				// action 기능 끝
				
				
				
				// 수정 버튼 클릭 시작
				$('#usend').on('click', function(){
					// 입력한 모든 값을 가져온다
					udata = $('#uform').serializeJSON();
					console.log(udata);
					
					// 모달창에 입력된 내용 지우기
					$('#uform .txt').val("");
					
					// 모달창 닫기
					$('#uModal').modal('hide');
					
					// 서버로 전송한다 - js함수 호출 - 동기방식은 가져와야하고, 비동기는 안가져와도 된다.. 흐음.. 동기 비동기..
					$.qnaUpdatePost();
				});
				
			
				// $('#qnaInsert').css('display', 'none');
				// $('#qna-write-btn-wrapper').css('display', 'none');
				
				
				
				


			});
				
				
				
				
				
			</script>
			
			
		
		
		
		
		
	</head>
	







	<body>


<!--
<div class="title">
	<h2>문의 게시판 / Q&amp;A</h2>
</div>

<div id="qnaNotice">
	상품에 관해 궁금한 점들을 작성해주시면 답변 드리겠습니다.
	타인에게 불쾌감을 주거나 사회적으로 부적절한 내용들은 삭제될 수 있습니다.
</div>
-->

<!-- 리스트 5개씩을 출력 -->
<div id="qnaresult"></div><br>

<!-- 페이지정보를 출력 공간 -->
<div id="qnapagelist"></div>

<div id="qnainsertbtn"></div>

<!-- 글쓰기 버튼 -->
<!--
<div class="container-fluid" id="qna-write-btn-wrapper">
	<div id="qna-write-btn-container">
		<input type="button" id="qnaInsert" value="글쓰기" style="display:none;"><br><br>
	</div>
</div>
-->


<!-- 모달2 전용 -->

<div class="container-fluid" id="qnabtn2-container" style="display:none;">
	<input type="button" id="qnaInsert2" value="글쓰기" style="display:none;"><br><br>
</div>




<!-- 검색창 -->

<br>
<div class='container' id='container-main'>
	<div class='wrapper' id='wrapper-qna'>
        <form action="submitQnA.jsp" method="post" id="qna-search-bar">
        	<div class='content' id="content-qna">
				<select class="form-select" id="stype">
					<option value="">a</option>
					<option value="writer">b</option>
					<option value="subject">c</option>
					<option value="content">d</option>
				</select>
        		<input class="form-control me-2" type="text" id="sword" placeholder="Search">
        	</div>
        	<button type="submit">검색</button>
        </form>





<!------- qna 글쓰기 modal --------->
<!------- qna 글쓰기 modal --------->
<!------- qna 글쓰기 modal --------->
<!------- qna 글쓰기 modal --------->
<!------- qna 글쓰기 modal --------->
<div class="modal" id="qnaModal">
	<div class="modal-dialog">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">문의글 작성하기</h4>
				<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
				<form name="qnaForm" id="qnaForm">
					<div id="qnaProdPic-container">
						<div id="qnaProdPic-container-head">
							<span>상품사진</span> <br>
						</div>
						<div id="qnaProdPic-container-body">
							<!-- <img src="./images/cat-jump.png" id="qnaProdPic" class="qnaInsertPic"> <br> -->
							<%-- <img src="<img src="<%= request.getContextPath() %>/images/prodImageView.do?imgId=<%=imgid %>" alt="<%= pvo.getProd_name() %>" width="300" height="300">" id="qnaProdPic" class="qnaInsertPic"> <br> --%>
							<!-- <img src="#" alt="상품이미지" width="300" height="300"> <br> -->
							
						</div>
					</div> <br>
					
					<label>상품명</label>&nbsp;&nbsp;&nbsp;
					<input type="text" class="txt" id="prod" name="prod_id"> <br>
					
					<label>ID</label>&nbsp;&nbsp;&nbsp;
					<input type="text" class="txt" id="writer" name="mem_id"> <br>
					
					<label>제목</label>
					<input type="text" class="txt" id="subject" name="qna_title"> <br>

					<label>암호</label>
					<input type="password"  class="txt" id="password" name="qna_pass"> <br>

					<label>내용</label>
		            <br>
        		    <textarea rows="5" cols="40"  class="txt" id="content"  name="qna_content"></textarea>
            		<br>
		            <br>
		            <div id="confirmbtn-container">
						<input type="button" value="완료" id="send">
		            </div>
				</form>
			</div>
			<!-- Modal footer -->
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-bs-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>


<!-- 드롭다운메뉴 상품명 출력용 -->
<div class="dropdown-container">
    <input type="text" id="search-box" placeholder="Type to search...">
    <ul id="dropdown" class="dropdown-list">
        <!-- 드롭다운 아이템이 동적으로 여기에 추가됨 -->
    </ul>
</div>





<!----- qna 글수정 Modal ----->
<!----- qna 글수정 Modal ----->
<!----- qna 글수정 Modal ----->
<!----- qna 글수정 Modal ----->
<!----- qna 글수정 Modal ----->
<div class="modal" id="uModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">게시글 수정하기</h4>
				<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
			</div>
			<!-- Modal body -->
			<div class="modal-body">
				<form name="ufrom" id="uform">
					<div id="qnaProdPic-container">
						<div id="qnaProdPic-container-head">
							<span>제품사진</span> <br>
						</div>
						<div id="qnaProdPic-container-body">
							<img src="" id="qnaProdPic" class="qnaInsertPic"> <br>
						</div>
					</div> <br>
					<label>상품명</label>
					<input type="text" class="txt" id="uprod" name="prod_id"> <br>
		
					<input type="hidden" id="unum" name="qna_no">
					<label>ID</label>
					<input type="text" class="txt" id="uwriter" name="mem_id"> <br> 
		
					<label>제목</label>
					<input type="text" class="txt" id="usubject" name="qna_title"> <br> 
		
					<label>암호</label>
					<input type="text"  class="txt" id="upassword" name="qna_pass"> <br> 
		
					<label>내용</label>
					<br>
					<textarea rows="5" cols="40"  class="txt" id="ucontent"  name="qna_content"></textarea>
					<br>
					<br>
					<input type="button" value="전송" id="usend">
				</form>
			</div>
			<!-- Modal footer -->
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>






	
<!-- 입력창(수정 내용 입력창)
	<div id="modifyform">
		<textarea rows="5" cols="50"></textarea>
		<input type="button" value="확인" id="btnok">
		<input type="button" value="취소" id="btnreset">
	</div>
-->




<!------- qna 글쓰기 modal2 --------->
<!------- qna 글쓰기 modal2 --------->
<!------- qna 글쓰기 modal2 --------->
<!------- qna 글쓰기 modal2 --------->
<!------- qna 글쓰기 modal2 --------->
<div class="modal" id="qnaModal2">
	<div class="modal-dialog">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">상품 목록 테스트</h4>
				<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
				<form name="qnaForm" id="qnaForm2">
					<div id="qnaProdPic-container2">
						<div id="qnaProdPic-container-head2">
							<span>상품사진</span> <br>
							<img src="<%= request.getContextPath() %>/images/empty-logo.png" alt="" width="300" height="300" id="qnaProdPic2" class="qnaInsertPic" onerror="this.src='<%= request.getContextPath() %>/images/empty-logo.png'; this.style.backgroundColor='white';">
						</div>
						<div id="qnaProdPic-container-body2">
							<!-- 여기에 사진 들어올 것 -->
						</div>
					</div> <br>
					
					
					
					<div class="input-wrapper">
						<div class="modal-titles">
							상품명<br>ID<br>제목<br>암호<br>
						</div>
						<div class="modal-inputs">
							<input type="text" class="txt prod" id="prod2" name="prod_id" placeholder="상품명 입력후 enter">
							<div class="prod-dropdown-cover"><div class="prod-dropdown"></div></div>
							<input type="text" class="txt" id="writer2" name="mem_id"> <br>
							<input type="text" class="txt" id="subject2" name="qna_title"> <br>
							<input type="password"  class="txt" id="password2" name="qna_pass"> <br>
						</div>
					</div>
					
					<label>내용</label>
		            <br>
        		    <textarea rows="5" cols="40"  class="txt" id="content2"  name="qna_content"></textarea>
            		<br>
		            <br>
		            <div id="confirmbtn-container2">
						<input type="button" value="완료" id="send2">
		            </div>
				</form>
			</div>
			<!-- Modal footer -->
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-bs-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>



<input type="button" style="display: none" id="hiddenbutton">







			
			








	    	</div>
	    </div>
	</body>
</html>