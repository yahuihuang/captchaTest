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
        驗證碼:  
    </label>  
    <div class="col-sm-8">  
        <input type="text" id="code" name="code" class="form-control" style="width:250px;"/>  
        <img id="imgObj" alt="驗證碼" src="/captchaTest/validateCode" 
        	tempSrc="/captchaTest/validateCode" onclick="changeImg()"/>  
        <a href="#" onclick="changeImg()">換一張</a>  
    </div>  
</div>  
  
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">  
    // 重新整理圖片  
    function changeImg() {  
        var imgSrc = $("#imgObj");  
        var tempSrc = imgSrc.attr("tempSrc");
        var src = changeUrl(tempSrc);
        imgSrc.attr("src", src);  
    }  
    //為了使每次生成圖片不一致,即不讓瀏覽器讀快取,所以需要加上時間戳  
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