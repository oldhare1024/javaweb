window.onload = function () {
    updataTotlePrice();
    var fruitTb1 = document.getElementById("tb1_fruit");
    var rows = fruitTb1.rows;
    for (var i = 0; i < rows.length - 1; i++) {
        var tr = rows[i]
        trBindeEvent(tr);
    }
    var subbtn=document.getElementById("button1");
    subbtn.onclick=insertFruit;
    var resetbtn=document.getElementById("button2");
    resetbtn.onclick=resetFruit;
}
function insertFruit(){
    var fn=document.getElementById("fname").value;
    var fp=document.getElementById("fprice").value;
    var fc=document.getElementById("fcount").value;
    if(fn!==""&&fp!==""&&fc!==""){
        var new_fruitTb1=document.getElementById("tb1_fruit");
        var row=new_fruitTb1.insertRow(1);
        var cell1=row.insertCell(0);
        var cell2=row.insertCell(1);
        var cell3=row.insertCell(2);
        var cell4=row.insertCell(3);
        var cell5=row.insertCell(4);
        cell1.innerHTML=fn;
        cell2.innerHTML=fp;
        cell3.innerHTML=fc;
        cell4.innerText = parseInt(fp) * parseInt(fc);
        var img=document.createElement("img");
        img.src="./image/OIP-C.jpg"
        img.className="delImg";
        cell5.appendChild(img);
        trBindeEvent(row);
        updataTotlePrice();
        resetFruit();
    }
    else {
        alert("内容不能为空，请重新输入");
    }
}

function trBindeEvent(tr){
    tr.onmouseover = showBGColor;
    tr.onmouseout = hideBGColor;
    var cells = tr.cells;
    var priceTD = cells[1];
    priceTD.onmouseover = showHand;
    priceTD.onclick = editPrice;
    var img =cells[4].firstChild;
    if(img&&img.tagName==="IMG"){
        img.onclick=delFruit;
    }
}

function resetFruit(){
    document.getElementById("fname").value="";
    document.getElementById("fprice").value="";
    document.getElementById("fcount").value="";
}
function delFruit(){
    if (event && event.srcElement && event.srcElement.tagName === "IMG"){
        if(window.confirm("是否确认删除当前库存记录")) {
            var img = event.srcElement;
            var tr = img.parentElement.parentElement;
            var fruitTb1 = document.getElementById("tb1_fruit");
            fruitTb1.deleteRow(tr.rowIndex);
            updataTotlePrice();
        }
    }
}

function editPrice() {
    if (event && event.srcElement && event.srcElement.tagName === "TD") {
        var priceTD = event.srcElement;
        if (priceTD.firstChild && priceTD.firstChild.nodeType === 3) {
            var oldPrice = priceTD.innerText;
            priceTD.innerHTML = "<input type='text' size='4'/>";
            var input = priceTD.firstChild;
            if (input.tagName === "INPUT") {
                input.value = oldPrice
                input.select();
                input.onblur = updataPrice;
                input.onkeyup=ckInput;
            }
        }
    }
}
function ckInput(){
    var kc=event.keyCode;
    //0~9：48~57
    //backspace: 8
    //enter: 13
    console.log(kc);
    if(!((kc>=48&&kc<=57)||kc===8||kc===13)){
        event.returnValue=false;
    }
    if(kc===13){
        event.srcElement.blur();
    }
}

function updataPrice() {
    if (event && event.srcElement && event.srcElement.tagName === "INPUT") {
        var input = event.srcElement;
        var newPrice = input.value;
        var priceTD = input.parentElement;
        priceTD.innerText = newPrice;
        updataSubTotalprice(priceTD.parentElement);
    }
}


function updataSubTotalprice(tr) {
    if (tr && tr.tagName === "TR") {
        var tds = tr.cells;
        var price = tds[1].innerText;
        var count = tds[2].innerText;
        tds[3].innerText = parseInt(price) * parseInt(count);
        updataTotlePrice();
    }
}

function updataTotlePrice() {
    var fruitTb1 = document.getElementById("tb1_fruit");
    var rows = fruitTb1.rows;
    var Totleprice = 0;
    for (var i = 1; i < rows.length - 1; i++) {
        var tr = rows[i];
        var SubTotalPrice = parseInt(tr.cells[3].innerText)
        Totleprice = Totleprice + SubTotalPrice;
    }
    rows[rows.length - 1].cells[1].innerText = Totleprice;
}

function showBGColor() {
    if (event && event.srcElement && event.srcElement.tagName === "TD") {
        var td = event.srcElement;
        var tr = td.parentElement;
        tr.style.backgroundColor = "navy";
        var tds = tr.cells;
        for (var i = 0; i < tds.length; i++) {
            tds[i].style.color = "white";
        }
    }
}

function hideBGColor() {
    if (event && event.srcElement && event.srcElement.tagName === "TD") {
        var td = event.srcElement;
        var tr = td.parentElement;
        tr.style.backgroundColor = "transparent";
        var tds = tr.cells;
        for (var i = 0; i < tds.length; i++) {
            tds[i].style.color = "black";
        }
    }
}

function showHand() {
    if (event && event.srcElement && event.srcElement.tagName === "TD") {
        var td = event.srcElement;
        td.style.cursor = "hand";
    }
}