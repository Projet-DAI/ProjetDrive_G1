<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
        Open Popup
    </button>

    <!-- 模态框 -->
    <div class="modal" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- 模态框头部 -->
                <div class="modal-header">
                    <h4 class="modal-title">User Information</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- 模态框主体 -->
                <div class="modal-body">
                    <form id="userInfoForm">
                        <div class="form-group">
                            <label for="username">Username:</label>
                            <input type="text" class="form-control" id="username" name="username">
                        </div>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" class="form-control" id="email" name="email">
                        </div>
                        <button type="button" class="btn btn-success" onclick="saveUserInfo()">Save</button>
                    </form>
                </div>

                <!-- 模态框底部 -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                </div>

            </div>
        </div>
    </div>
    
    <script>
        function saveUserInfo() {
            // 使用 Ajax 将用户信息发送到后端
            $.ajax({
                url: 'SaveUserInfoServlet',
                type: 'POST',
                data: $('#userInfoForm').serialize(),
                success: function(response) {
                    alert(response);
                    $('#myModal').modal('hide');
                },
                error: function(error) {
                    alert('Error saving user information!');
                }
            });
        }
    </script>
</body>
</html>