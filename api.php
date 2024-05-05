<?php
include_once ('buscar.php');
include_once ('guardar.php');

$opc = $_SERVER['REQUEST_METHOD'];
switch ($opc) {
    case 'GET':
        Buscar::buscar();
        break;
    case 'POST':
        Guardar::guardarEstudiantes();
        break;

    default:
        # code...
        break;
}