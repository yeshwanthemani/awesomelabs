/* Mysql stored procedure to retreive data from database "getfrom"
uid is auto incremented column id in TUserDetails table
CREATE PROCEDURE getfrom(IN id int)
BEGIN 
select * from TUserDetails where uid='id';
END;
*/

/* ON SUCCESS  
{
    "Success": 1,
    "prod": [
        {
            "uid": "1",
            "uname": "Abcdef",
            "uaddr": "Greenpark Colony ,Guntur",
            "uphone": "8888888000"
        }
    ]
}

ON FAILURE 
{
    "Success": 0,
    "msg": "No Details Found with the given id"
}

*/



<?php 

include_once("connect.php");
$table="TUserDetails";

 if(isset($_GET['submit']))
 {
	$uid=$_GET['uid'];
		
	
	if(!empty($uid))
	{
		$sql="CALL getfrom('".$uid."')";
		$res=$conn->query($sql);
		if(mysqli_num_rows($res)>0)
		{
			$result=mysqli_fetch_assoc($res);
			$row['uid']=$result['uid'];
			$row['uname']=$result['uname'];
			$row['uaddr']=$result['uaddr'];
			$row['uphone']=$result['uphone'];
			$response["Success"]=1;
			$response["prod"]=array();
			array_push($response["prod"],$row);
			echo json_encode($response);
			
		}
		else
		{
			$response["Success"]=0;
			$response["msg"]="No Details Found with the given id";
			echo json_encode($response);
		}

			
	}
	else 
	{
		echo json_encode("Please Enter Column id to Display Details");
	}
 }
 else
 {
	echo json_encode("Please Click Submit to see Details");
 }

?>
	
