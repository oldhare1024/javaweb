function delFruit(fid){
    if(confirm('�Ƿ�ȷ��ɾ����')){
        window.location.href='fruit.do?fid='+fid+'&operate=del';
    }
}
function page(pageNo){
    window.location.href="fruit.do?pageNo="+pageNo;
}