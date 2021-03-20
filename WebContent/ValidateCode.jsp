<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>ValidateCode</title>
</head>
<body>
<div class="form-group  col-lg-6">  
    <label for="id" class="col-sm-4 control-label">  
        ���ҽX:  
    </label>  
    <div class="col-sm-8">  
        <input type="text" id="code" name="code" class="form-control" style="width:250px;"/>  
        <img id="imgObj" alt="���ҽX" src="/captchaTest/validateCode" 
        	tempSrc="/captchaTest/validateCode" onclick="changeImg()"/>  
        <a href="#" onclick="changeImg()">���@�i</a>  
    </div>  
</div>  
  
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">  
    // ���s��z�Ϥ�  
    function changeImg() {  
        var imgSrc = $("#imgObj");  
        var tempSrc = imgSrc.attr("tempSrc");
        var src = changeUrl(tempSrc);
        imgSrc.attr("src", src);  
    }  
    //���F�ϨC���ͦ��Ϥ����@�P,�Y�����s����Ū�֨�,�ҥH�ݭn�[�W�ɶ��W  
    function changeUrl(url) {  
        var timestamp = (new Date()).valueOf();  
        var index = url.indexOf("?",url);  
        if (index > 0) {  
            url = url.substring(0, url.indexOf(url, "?"));  
        }  
        url = url + "?timestamp=" + timestamp;  
        return url;  
    }  
</script>  
</body>
</html>