<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>fileUpload</title>
</head>
<body>
<g:uploadForm name = "upload" enctype="multipart/form-data" method="post" controller="util" action="upload">
    <input type="file" name="myfile"/>
    <input type="submit" name="submit"/>
</g:uploadForm>
</body>
</html>