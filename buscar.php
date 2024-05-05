<?php
include_once('conexion.php');

class Buscar{

    public static function buscar(){
        $curso = $_GET['curso'];
        $paralelo = $_GET['paralelo'];

        $sql = "select id from cursos where curso = '$curso' and paralelo = '$paralelo'";

        $cn = Conexion::conectar();
        $result = $cn->prepare($sql);
        $result->execute();

        $data = $result->fetchAll(PDO::FETCH_ASSOC);
        $idCurso = $data[0]['id'];
    
        $sql = "select * from estudiantes where curso = '$idCurso'";
        $result = $cn->prepare($sql);
        $result->execute();
        $data = $result->fetchAll(PDO::FETCH_ASSOC);
        echo json_encode($data);

    }
}