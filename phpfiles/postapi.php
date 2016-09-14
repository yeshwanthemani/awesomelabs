/* Mysql stored procedure to insert data into database
uid is auto incremented column id in TUserDetails table
CREATE PROCEDURE insinto(IN name varchar(50),IN addr varchar(50),IN phone int(10))
BEGIN 
insert into TUserDetails(uname,uaddr,uphone) values(name,addr,phone);
END;
*/



<?php 

include_once("connect.php");
$table="TUserDetails";

 if(isset($_POST['submit']))
 {
	$uname=$_POST['uname'];
	$uaddr=$_POST['uaddr'];
	$uphone=$_POST['uphone'];

	if(!empty($uname) && !empty($upass) && !empty($uphone))
	{
		$sql="CALL insinto('".$uname."' ,'".$uaddr."','".$uphone,"')";
		if($conn->query($sql)==TRUE)
		{
			echo json_encode("Details Entered into Database Succesfully");
		}
		else
		{
			echo json_encode("Oops,There is a problem in entering your details.Please try Again!");
		}	
	}
	else 
	{
		echo json_encode("You Must Fill All fields");
	}
 }
 else
 {
	echo json_encode("Please Submit your details");
 }

?>
	
