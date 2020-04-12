# Servlet_3

##공지사항(포인트랑 비슷)
- 글쓰기, 수정, 삭제 id가  admin 만 가능
- 글 리스트 페이지(최신순으로 출력)
- 리스트 페이지에서 글쓰기 버튼 클릭을 누르면 글쓰기로 이동
- 리스트 페이지에서 글 제목을 누르면 글 상세보기로 이동
- 글 상세보기에서 글 수정, 글삭제 버튼 존재
- 글 삭제 버튼을 누르면 확인, 취소 창을 띄우고 확인을 누르면 삭제
- 글 수정을 누르면 글 수정페이지로 이동
- (옵션)조회수 늘리기, 글 상세보기 하면 조회수 1 증가(sequence)

### notice
- 글쓰기 리스트 출력(글쓰기 버튼)(o)
- 글쓰기(수정, 삭제버튼) (o)
- 글 상제 정보 출력(제목 누르면 상세페이지)(o)
- 글 수정(수정누르면 수정페이지로 수정하고, 저장버튼)
- 글 삭제(o)

### url
- /notice/noticeList		noticeList.jsp		GET
- /notice/noticeAdd			noticeAdd.jsp   	POST
- /notice/noticeAdd			noticeAdd.jsp   	GET
- /notice/noticeSelect		noticeSelect.jsp	GET
- /notice/noticeMod			noticeMod.jsp		GET
- /notice/noticeMod			noticeMod.jsp		POST
- /notice/noticeDelete		noticeDelete.jsp	GET


##기능
### Member
- 회원가입(insert)
- 로그인(SelectOne)
- 회원 수정(update)
- 회원 탈퇴(delete)
- myPage
##JSP
- /WEB-INF/views/member/**

##URL
- /member/memberJoin		memberJoin.jsp		GET
- /member/memberJoin							POST
- /member/memberLogin		memberLogin.jsp		GET
- /member/memberLogin							POST
- /member/memberPage		memberPage.jsp		GET
- /member/memberUpdate	memberUpdate.jsp	GET
- /member/memberUpdate						POST
- /member/memberDelete						GET

### Point
- point list 출력
- point 등록
- point 상세 정보 출력
- point 수정
- point 삭제
 
##JSP
- pointList.jsp	: List 출력
- pointAdd.jsp	: 입력폼
- pointSelect.jsp	:point 상세 정보 출력
- pointMod.jsp	: point 수정 폼
 
##URL 주소 				/ JSP 연동			/Method
###	/WEB-INF/views/point/**
- /point/pointList		/pointList.jsp		/GET
- /point/pointAdd		/pointAdd.jsp		/GET
- /point/pointADD		/					/POST
- /point/pointSelect	/pointSelect.jsp	/GET
- /point/pointMod		/pointMod.jsp		/GET
- /point/pointMod		/					/POST
- /point/pointDelete	/					/GET
 


