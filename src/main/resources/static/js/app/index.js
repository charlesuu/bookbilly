var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });

        $('#btn-book-save').on('click', function () {
            _this.bookSave();
        });

        $('#btn-book-loan').on('click', function () {
            _this.bookLoan();
        });

        $('#btn-book-return').on('click', function () {
            _this.bookReturn();
        });
    },
    save : function () {
        var data = {
            name: $('#name').val(),
            age: $('#age').val()
        };

        $.ajax({
            type: 'POST',
            url: '/user',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('사용자가 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert('서버 내부 오류입니다. (문의 - 이현호 선생님)');
            //alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            id: $('#id').val(),
            name: $('#name').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/user',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('이름이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert('서버 내부 오류입니다. (문의 - 이현호 선생님)');
            //alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/user/'+id,
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('사용자가 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert('서버 내부 오류입니다. (문의 - 이현호 선생님)');
            //alert(JSON.stringify(error));
        });
    },
    bookSave : function () {
        var data = {
            name: $('#book-name').val()
        };

        $.ajax({
            type: 'POST',
            url: '/book',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('도서가 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert('서버 내부 오류입니다.(문의 - 이현호 선생님)');
            //alert(JSON.stringify(error));
        });
    },
    bookLoan : function () {
        var data = {
            userName: $('#name').val(),
            bookName: $('#book-name').val()
        };

        $.ajax({
            type: 'POST',
            url: '/book/loan',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('대출 성공 : 대출 기록이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert('대출 실패 : 등록되지 않았거나, 이미 대출 된 도서입니다.');
            //alert(JSON.stringify(error));
        });
    },
    bookReturn : function () {//작업 중
        var data = {
            userName: $('#name').val(),
            bookName: $('#book-name').val()
        };

        $.ajax({
            type: 'PUT',
            url: '/book/return',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('반납 성공 : 반납 처리 되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert('반납 실패 : 대출한 적 없는 도서입니다');
            //alert(JSON.stringify(error));
        });
    }
};

main.init();