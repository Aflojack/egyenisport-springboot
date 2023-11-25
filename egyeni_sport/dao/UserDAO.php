<?php

require("util/sqlService.php");

class UserDAO{
    private $sqlservice;
    public function __construct(){
        $this->sqlservice=new sqlService();
    }

    function createUser(string $felh,string $jelszo,string $nev,int $admin): void{
        if($this->sqlservice->isConnected()){
            $conn=$this->sqlservice->getConn();
            $sql="INSERT INTO felhasznalo(felhasznalonev,jelszo,nev,admin) VALUES (?, ?, ?, ?)";
            $stmt = mysqli_prepare($conn,$sql);
            mysqli_stmt_bind_param($stmt, "sssd",$felh,$jelszo,$nev,$admin);
            mysqli_stmt_execute($stmt);
            mysqli_close($conn);
        }
    }

    function searchUserByUsername(string $felhasznalonev){
        if($this->sqlservice->isConnected()){
            $conn=$this->sqlservice->getConn();
            $sql="SELECT * FROM felhasznalok WHERE felhasznalonev=?";
            $stmt = mysqli_prepare($conn,$sql);
            mysqli_stmt_bind_param($stmt, "s",$felhasznalonev);
            mysqli_stmt_execute($stmt);
            mysqli_close($conn);
        }
    }
}
?>