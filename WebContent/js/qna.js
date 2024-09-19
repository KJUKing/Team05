/**
 * 
 */

$.qnaReplyList = function() {
	$.ajax({
		url : `${mypath}/qnaReplyList.do`,
		data : {bonum : vidx}, // {bonum : reply.bonum}
		type : 'get', // contentType 이 application/json 일때는 post 무조건인거, dataType 말고.
		success : res => {
			// alert(res);
			console.log('댓글 리스트 결과는 성공 ' + res);
			
			rcode = "";
			
			// 댓글 리스트 res 출력
			$.each(res, function(i,v){
				cont = v.qna_answer;
				cont = cont.replaceAll(/\n/g, "<br>");
				
				rcode += `<div class="reply-body">
							<div class="p12">
								<hr>
								<p class="p1">
									답변
							    </p>
							    <p class ="p2">`
								//if(uvo != null && uvo.mem_name == v.name){
								//	rcode += `<input idx="${v.renum}" type="button" value="댓글수정" name="r_modify" class="action">
								//	<input idx="${v.renum}" type="button" value="댓글삭제" name="r_delete" class="action">`
								//}
								rcode += `</p>
							</div>
							<p class="rp3">
								${cont}
							</p>
						</div>`;
				
				// $(this).parents('.card') // 이렇게 하고싶은데 this가 애매해서 안됨
				// target변수는 제목, 등록 을 클릭할때 this를 받은 변수
				// car 헤더를 찾으려고 하니까, 삼촌이라서 바로 못감. 그래서 조상으로 간 다음에, 다시 card-body로 내려감.
			})
			$(target).parents('.card').find('.reply-body').remove();
			// $(target).parents('.card').find('.card-body').append(rcode);
			$(target).parents('.card').find('.p3').after(rcode);
		},
		error : xhr => {
			alert("댓글 리스트 실패: " + xhr.status);
		},
		dataType : 'json'
	})
}


// 문의글 댓글 달기
$.qnaInsertReply = function() {
	$.ajax({
		// 이것들 순서 바꿔도 작동 문제 없음.
		url : `${mypath}/qnaInsertReply.do`,
		data : JSON.stringify(reply),
		contentType : 'application/json',
		type : 'post',
		success : res => {
			// alert(res.flag);
			// flag가 ok면 댓글 리스트가 출력되도록 해야지
			$.qnaList();
		},
		error : xhr => {
			alert("error: " + xhr.status);
		},
		dataType : 'json'
	})
}



// 문의글 삭제하기
$.qnaDeletePost = function() {
	$.getJSON(
		`${mypath}/qnaDeletePost.do`,
		{num : vidx},
		function(res) {
			$.qnaList();
		}
	)
}


// 문의글 수정하기
$.qnaUpdatePost = function() {
	$.ajax({
		url : `${mypath}/qnaUpdatePost.do`,
		data : JSON.stringify(udata),
		type : 'post',
		contentType : 'application/json; charset=utf-8',
		success : res => {
			
			/*
			alert(res.flag);
			// ok이면 화면수정
			// servlet 새로 만들었으면 서버 껐다가 다시 키기
			// 화면 수정 = modal에 입력한 내용 가져와서 본문에 출력
			
			// modal에 입력한 내용 가져오기
			vwr = $('#uwriter').val();
			vno = $('#unum').val(); // 이 셋은 수정 불필요
			vp = $('#upassword').val();
			vs = $('#usubject').val();
			vc = $('#ucontent').val();
			
			// 이렇게도 가능함
			// 이렇게 하면 모달창 내용 지우기랑 모달창 닫기를, jsp에 #usend 함수에 그대로 둘 수 있음.
			// 바로 위에 $('#') 이거랑 다르게, 할 수 있음.
			// jsp에서 내용을 지웠기 때문에 위에 val 써서 데이터를 가져왔어야 하는건데,
			// 아래 방법으로 하면 jsp에서 내용 안지워도 됨. 근데 지워야 하는거 아녀?
			// vsub = udata.subject;
			// vm = udata.mail;
			// vc = udata.content;
			
			// 원글 본문의 내용을 변경
			$(vcard).find('a').text(vs);
			$(vcard).find('.em').text(vm);
			
			vc = vc.replaceAll(/\n/g, "<br>"); // 줄내림 n으로 되어있는거를 다 <br>로 바꾸라는 말
			$(vcard).find('.p3').html(vc);
			
			// 모달창 내용 지우기
			$('#uform .txt').val("");
			
			// 모달창 닫기
			$('#uModal').modal('hide');
			*/
			$.qnaList();
		},
		error : xhr => {
			alert("update error: " + xhr.status);
		},
		dataType : 'json'
	})
}



// 문의글 신고하기
$.reportPost = function() {
	// $.post(url, data, success, dataType); // 이 단축형으로 보내려고 하니까, contentType 넣는게 없어서 여기선 안됨
	$.ajax({
		url : `${mypath}/reportPost.do`,
		data : JSON.stringify(udata),
		type : 'post', // json.stringify 이거는 post만 가능. get은 왜 불가능할까?
		contentType : 'application/json; charset=utf-8',
		success : res => {
			alert(res.flag);
			// ok이면 화면수정
			// servlet 새로 만들었으면 서버 껐다가 다시 키기
			// 화면 수정 = modal에 입력한 내용 가져와서 본문에 출력
			
			// modal에 입력한 내용 가져오기
			// vwr = $('#uwriter').val();
			// vno = $('#unum').val(); // 이 셋은 수정 불필요
			//vp = $('#upassword').val();
			vs = $('#usubject').val();
			vm = $('#umail').val();
			vc = $('#ucontent').val();
			
			// 이렇게도 가능함
			// 이렇게 하면 모달창 내용 지우기랑 모달창 닫기를, jsp에 #usend 함수에 그대로 둘 수 있음.
			// 바로 위에 $('#') 이거랑 다르게, 할 수 있음.
			// jsp에서 내용을 지웠기 때문에 위에 val 써서 데이터를 가져왔어야 하는건데,
			// 아래 방법으로 하면 jsp에서 내용 안지워도 됨. 근데 지워야 하는거 아녀?
			// vsub = udata.subject;
			// vm = udata.mail;
			// vc = udata.content;
			
			// 원글 본문의 내용을 변경
			$(vcard).find('a').text(vs);
			$(vcard).find('.em').text(vm);
			
			vc = vc.replaceAll(/\n/g, "<br>"); // 줄내림 n으로 되어있는거를 다 <br>로 바꾸라는 말
			$(vcard).find('.p3').html(vc);
			
			// 모달창 내용 지우기
			$('#uform .txt').val("");
			
			// 모달창 닫기
			$('#uModal').modal('hide');
			
		},
		error : xhr => {
			alert("Error: " + xhr.status);
		},
		dataType : 'json'
	})
	
}




// 게시글 쓰기
$.qnaInsertPost = function() {
	
	// 이렇게도 가능
	// data : "id=" + idvalue + "&name=" + namevalue
	// type : 'get / type : 'post'
	// contentType : 'aaplication/x-www-form-urlencode'
	
	// data : {id : idvalue , name : namevalue}
	// type : 'get / type : 'post'
	// contentType : 'application/x-www-form-urlencode'
	
	$.ajax({
		url : `${mypath}/qnaInsertPost.do`,
		data : JSON.stringify(fdata3),
		type : 'post',
		contentType : 'application/json',
			success : res => {
			// 성공했으면 ok 띄우는거
			// alert(res.flag);
			
			// 리스트 페이지 혹은 메인페이지로 이동
			
			// 메인페이지로 이동하기
			// location.href = "main"
			// location.href = `${mypath}/start/index.jsp`; // ${} 이거 뭐냐고.. getcontextpath는 또 어디고..
			
			// 리스트 페이지로 이동하기
			$.qnaList();
		},
		error : xhr => {
			alert("Error: " + xhr.status);
		},
		dataType : 'json'
	})
}




$.qnaList = function() {
	
	/* stype = $("#stype option:selected") id가(#) stype인 것의 옵션 중 선택된 것.의 값().을 앞뒤 공백 제거해서 대입시켜라(=) stype이라는 변수에 */
	stype = $("#stype option:selected").val().trim();
	sword = $('#sword').val().trim();
		
	datas = {page : currentPage , stype : stype , sword : sword};
	
	$.ajax({
		/* 이거 ${} 이거 안에는 변수 넣기. 변수밖에 안됨? 어제 뭐 하니까 안됐던거는 왜 안됨 */
		url : `${mypath}/qnaList.do`,
		type : 'POST',
		data : JSON.stringify(datas), /* 이거 데이터 보내는거잖아, stringify해서 보내기, 직렬화 해서 datas를 보내기, 근데 datas는 형식인데? */
		contentType : 'application/json',
		success : res =>{
			console.log(res);
			
			code = `<div class="container mt-3">`;
			code += `<div id="accordion">`;
			$.each(res.datas, function(i, v){
				// 지금부터 엄청 복잡하다.
				// 내용 가져온다 - 엔터로 저장되어 있는것을 <br>로 바꾸기 위해서
				stat = v.qna_status;
				cont = v.qna_content;
				console.log(cont);
				// cont = cont.replaceAll(/\n/g, "<br>")
				code += `<div class="card">
					<div class="card-header" name="title-space" style="padding : 0px;">
						<a class="btn action" idx="${v.qna_no}" name="title" id="title" style="width : 100%; text-align : left" data-bs-toggle="collapse" href="#collapse${v.qna_no}">
							${v.qna_title}`
							if(stat=='Y') code += `<span style="color : red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;답변완료</span>`
						code += `</a>
					</div>
					<div id="collapse${v.qna_no}" class="collapse" data-bs-parent="#accordion">
						<div class="card-body">
							<div class="p12">
								<p class="p1">
									ID: <span class="wr">${v.mem_id}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									작성일: <span class="da">${v.qna_date}</span>
								</p>
								<hr>

							</div>
								문의내용</p>
							<p class="p3">
								${cont}
							</p>
							<hr>`
								// if(uvo != null && uvo.memId == 'admin') // 이거 나중에 풀어서 사용
								if(loginId == 'admin'){ // test용
								code += `<p class="p4">
									<textarea rows="" style="width: 100%;"></textarea>`
									if(stat=='Y') code += `<input idx="${v.qna_no}" type="button" value="수정" name="reply" class="action" status=${v.qna_status}>`
									else code += `<input idx="${v.qna_no}" type="button" value="등록" name="reply" class="action" status=${v.qna_status}>`
									code += `<input idx="${v.qna_no}" type="button" value="삭제" name="delete" class="action" status=${v.qna_status}>
								</p>`
								} else if(v.mem_id == uvo.memId) {
								// } else if(loginId == 'p002') {
									code += `<p class="p4"><input idx="${v.mem_id}" type="button" value="수정" name="modify" class="action">
									&nbsp;<input idx="${v.qna_no}" type="button" value="삭제" name="delete" class="action"></p>`
								} else {
									code += `<p class="p4"><input type="button" value="신고" name="report" class="action"></p>`
								}
								code += `
						</div>
					</div>
				</div>`
				// input에 준 idx 속성은 임의로 준것. 주면 뭐 되는데?
			})
			code += `</div></div>`;
			
			// 리스트 출력
			$('#result').html(code);
			
			// page 정보 출력
			vpage = $.postList(res.sp, res.ep, res.tp);
			$('#pagelist').html(vpage);
		},
		error : xhr =>{
			alert("qnalist오류: " + xhr.status);
		},
		dataType : 'json'
	}) // ajax 끝
}



$.postList = function(sp, ep, tp) {
	
	// 이전
	pager = "";
	pager += '<ul class="pagination">';
	if(sp > 1) {
		pager += `<li class="page-item"><a id="prev" class="page-link" href="#">이전</a></li>`;
	}
	
	// currentPage = 7(마지막페이지) 마지막 페이지의 모든 데이터 삭제할 경우
	// currentPage의 값이 새로 구한 totalPage(tp)로 변경되어야 한다
	if(currentPage > tp) currentPage = tp;
	
	// 페이지 번호
	for(i = sp; i <= ep; i++) {
		if(currentPage == i) {
			pager += `<li class="page-item active"><a class="page-link pageno" href="#">${i}</a></li>`;
		} else {
			pager += `<li class="page-item"><a class="page-link pageno" href="#">${i}</a></li>`;
		}
	}
	
	// 다음
	if(ep < tp) {
		pager += `<li class="page-item"><a id="next" class="page-link" href="#">다음</a></li>`;
	}
	
	pager += "</ul>";
	return pager;
}






// 자동완성용 상품명 모두 미리 가져오기 // db 부하 줄이기
$.getAllProdName = function() {
	$.ajax({
		url : `${mypath}/getAllProdName.do`,
		type : 'POST',
		contentType : 'application/json',
		success : (data) => {
			products = data.apn;
			console.log(products[0]);
		},
		error : xhr =>{
			alert("getAllProdNameForAutoComplete 오류: " + xhr.status);
		},
	})
}















$.testQnaList = function() {
	
	test1 = 'use ajax to get qna list';
	
	ajax
	
}















