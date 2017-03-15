/*
 * 
 * Document   : changePass.js
 * Created on : 15-Mar-2017, 01:50:15
 * Author     : Rory Raeper
 */

function changePass(){
    var html;
    html = [
        '<form method="POST"  action="Profile">',
        '<br><div class="input-group"><span class="input-group-addon">Current Password</span>',
        '<input type="password" name="currPass" class="form-control" placeholder="Enter Current Password"></div><br>',
        '<div class="input-group"><span class="input-group-addon">New Password</span>',
        '<input type="password" name="newPass" class="form-control" placeholder="Enter a New Password"></div><br>',
        '<div class="input-group"><span class="input-group-addon">Confirm New Password</span>',
        '<input type="password" name="confPass" class="form-control" placeholder="Confirm New Password"></div><br>',
        '<div class="span12" style="text-align:center"><div class="STYLE">',
        '<input type="submit" class="btn btn-success" value="Submit"></div></div>',
        '</form>'
    ].join("\n");
    $(".ChangePass").append(html);
}
