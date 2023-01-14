/**
 * 
 */
console.log('연결?완')
const showFeilds = {
	bookCode: "도서코드",
	bookTitle: "도서명",
	bookAuthor: "저자",
	bookPress: "출판사",
	bookPrice: "금액"
};
const addColumn = {
	col1: ['button', '삭제'],
	col2: ['input', 'checkbox']
}
//리스트 출력할 테이블
function makeTable(aryData = [], parent) {
	let tbl = document.createElement('table');
	tbl.setAttribute('border', '1');
	let thd = document.createElement('thead');
	let tbd = document.createElement('tbody');
	let tr = document.createElement('tr');

	//thead영역
	let fields = showFeilds;
	for (const field in fields) {
		let th = document.createElement('th');
		th.innerText = fields[field];
		tr.append(th);
	}

	for (const col in addColumn) {
		let th = document.createElement('th');
		th.innerHTML = addColumn[col][0] == 'button' ? addColumn[col][1] : '<input type="checkbox">';
		tr.append(th);
	}
	thd.append(tr);
	tbl.append(thd);

	//tboby
	for (const data of aryData) {
		let tr = makeTr(data);
		tbd.append(tr);
	}
	tbl.append(tbd);
	parent.append(tbl);
}




//tr만드는 함수 
function makeTr(data) {
	console.log(data)
	tr = document.createElement('tr');

	for (const field in showFeilds) {
		let td = document.createElement('td');
		td.innerText = data[field];
		tr.append(td);
	}

	for (const col in addColumn) {
		let td = document.createElement('td');
		let elem = document.createElement(addColumn[col][0]);
		elem.innerText = addColumn[col][1];
		if (elem.innerText == '삭제') {
			elem.setAttribute('class', 'cancelbtn')
		};
		if (addColumn[col][0] == 'input') {
			elem.setAttribute('type', addColumn[col][1])
		}

		td.append(elem);
		tr.append(td);
	}
	return tr;
}
//삭제
function deleteBookFnc() {
	let id = this.parentElement.parentElement.getAttribute('id');
	id = id.substring(7);
	fetch('ajaxBookDel.do', {
			method: 'post',
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			body: 'id=' + id
		})
		.then(result => result.json())
		.then(result => {
			if (result.retCode == 'Success') {
				document.querySelector('#notice_' + result.id).remove()
			} else if (result.retCode == 'False') {
				alert('처리중 오류 발생 ')
			}
		})
		.catch(err => console.log(err));
}

//리스트 출력
fetch('ajaxBookList.do')
	.then(result => result.json())
	.then(data => {
		const parentEl = document.getElementById('show');

		//테이블 생성
		makeTable(data, parentEl);
		console.log(data);

		//삭제버튼
		let trs = document.querySelectorAll('#show tbody tr');
		for (const trElem of trs) {
			console.log(trElem.querySelector('td:nth-child(6)>button'))
			trElem.setAttribute('id', 'notice_' + trElem.firstChild.innerText)
			trElem.querySelector('td:nth-child(6)>button').addEventListener('click', deleteBookFnc)
		}


	})
	.catch(err => console.log(err))


document.addEventListener('DOMContentLoaded', function () {
	//저장누르면 폼에 submit
	document.querySelector('form[name=bookFrm]').addEventListener('submit', saveBookFnc);
})

//도서정보 저장  
function saveBookFnc(e) {
	e.preventDefault();

	// console.log('저장버튼 호출')

	let code = document.getElementById('bookCode').value;
	let title = document.getElementById('bookTitle').value;
	let author = document.getElementById('bookAuthor').value;
	let press = document.getElementById('bookPress').value;
	let price = document.getElementById('bookPrice').value;
	console.log(code, title, author, press, price)

	if (!code || !title || !author || !press || !price) {
		alert('값을 입력해주세요');
		return
	}

	// let frm = document.querySelector('form[name=bookFrm]');
	// let formData = new FormData(frm);
	fetch('ajaxBookAdd.do', {
			method: 'post',
			headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
			body: 'code=' + code + '&title=' + title + '&author=' + author + '&press=' + press + '&price=' + price
		})
		.then(result => result.json())
		.then(result => {
			let tr = makeTr(result);
			document.querySelector('#show tbody').append(tr);


			//새로 등록되는 값에 대한 삭제버튼
			let trs = document.querySelectorAll('#show tbody tr');
			for (const trElem of trs) {
				console.log(trElem.querySelector('td:nth-child(6)>button'))
				trElem.setAttribute('id', 'notice_' + trElem.firstChild.innerText)
				trElem.querySelector('td:nth-child(6)>button').addEventListener('click', deleteBookFnc)
			}
		})
		.catch(err => console.log(err))

}