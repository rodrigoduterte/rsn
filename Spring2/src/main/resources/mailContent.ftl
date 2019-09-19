<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title></title>
  </head>
  <body>
    <p>&nbsp;<span style="font-family: Carlito;">&nbsp;<span style="font-family: Ebrima;">
          <br>
        </span></span></p>
    <p><span style="font-family: Carlito;"><span style="font-family: Ebrima;"><strong><b><br>
            </b></strong></span></span></p>
    <p><span style="font-family: Carlito;"><span style="font-family: Ebrima;"><strong><b>&nbsp;
              Thank you ${firstname} for registering with Coollaborate!</b></strong></span></span></p>
    <table style="width: 272px; height: 108px;" border="1">
      <tbody>
        <tr>
          <td style="margin-left: 34px; width: 88px;"><span style="font-family: Microsoft JhengHei;">${username}</span></td>
          <td>&nbsp;&nbsp;&nbsp;&nbsp; </td>
        </tr>
        <tr>
          <td style="width: 108.833px;"><span style="font-family: Microsoft JhengHei;">Firstname</span></td>
          <td style="width: 147.167px;">
            <div style="margin-left: 40px;"><br>
            	${firstname}
            </div>
          </td>
        </tr>
        <tr>
          <td><span style="font-family: Microsoft JhengHei;">Middlename</span></td>
          <td><br>
          	${middlename}
          </td>
        </tr>
        <tr>
          <td><span style="font-family: Microsoft JhengHei;">Lastname</span></td>
          <td style="text-align: left;"><br>
          	${lastname}
          </td>
        </tr>
      </tbody>
    </table>
    <p> &nbsp;&nbsp;&nbsp; <b style="font-family: Ebrima;">Click the link below
        to activate your account</b></p> 
    <p><b style="font-family: Ebrima;">&nbsp;&nbsp; 
    <a href="${server}/user/activate/${activateName}">
    ${server}/activate/${activateName}
    </a><br>
      </b></p>
  </body>
</html>
