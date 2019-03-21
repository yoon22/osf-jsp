package com.osf.test.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.osf.test.service.PBoardService;
import com.osf.test.service.impl.PBoardServiceImpl;

public class PBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static String savePath = "D:\\study\\workspace\\osf-jsp\\WebContent\\upload";
    private PBoardService pbs = new PBoardServiceImpl(); 

    public PBoardServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.replace("/pboard/","");
		if("list".equals(uri)) {
			request.setAttribute("pBoardList",pbs.selectPBoardList());
			RequestDispatcher rd = request.getRequestDispatcher("/views/photo-board/list.jsp");
			rd.forward(request,response);
			return;
			 
		}else {
			try {
				int pbNum = Integer.parseInt(uri);
				request.setAttribute("pBoard", pbs.selectPBoard(pbNum));
				RequestDispatcher rd = request.getRequestDispatcher("/views/photo-board/view.jsp");
				rd.forward(request,response);
			}catch(NumberFormatException e) {
				throw new ServletException("상세조회는 번호만 가능합니다. ");			}
		
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.replace("/pboard/","");
		if("insert".equals(uri)) {
			DiskFileItemFactory dfiFactory= new DiskFileItemFactory();
			//톰캣에있는 클래스를 쓰는게 아니라 common? 자르에 있는 거 쓰는거임.
			//tmp디렉토리설정, 메모리 얼마나 쓸지 설정하는것임. 
			String tmpPath = System.getProperty("java.io.tmpdir");
			//파일경로를 집어넣음~~~~~~~
			File tmpFile = new File(tmpPath);
			//해당폴더를 관리할수있는 tmpFile변수 만듦~~~
			dfiFactory.setRepository(tmpFile);
			//폴더를 임시저장소로 사용하라고 한것임~~~~!
			dfiFactory.setSizeThreshold(10*1024*1024);
			//경로설정!!!!
			
			ServletFileUpload sfu = new ServletFileUpload(dfiFactory);
			//톰캣에있는 클래스를 쓰는게 아니라 common? 자르에 있는 거 쓰는거임.
			//request가 파라미터를 못불러오니깐 만들어놓음 ㅇㅅㅇ..
			sfu.setHeaderEncoding("utf-8");
			sfu.setSizeMax(20*1024*1024);  
			//  ↑전체파일 사이즈!! 원래 파일1개에 10 3개 올릴거면 () 안에 30메가 여야함.
			//파일 한개랑 숫자 같은거 말도 안됨~! 
			sfu.setFileSizeMax(20*1024*1024); //파일한개
			
			
			try {
				List<FileItem> fileList = sfu.parseRequest(request);
				//insert.jsp 의 key,value 가 List 형태로 fileList에 넣어짐.
				//request가 값을 못받아오니까..........sfu이용해서 parsing? 해줌. 
				//enctype="multipart/form-data" 을 통해 동작할수 있는것임. 
				Map<String,String> pBoard = new HashMap<>();
				//맵을 만드는 이유 : service -> DAO 넘기면서 insert 하기 편하기 위해서 ... ? ? ? 
				for(int i=0;i<fileList.size();i++) {
					FileItem fi = fileList.get(i);
					if(fi.isFormField()) {
						//데이타베이스에 저장할것
						pBoard.put(fi.getFieldName(), fi.getString("utf-8"));
					}else {
						String rFileName = fi.getName();
						String extName = rFileName.substring(rFileName.lastIndexOf(".")+1);
						String fileName = System.currentTimeMillis()+"";
						File saveFile = new File(savePath+ "\\" + fileName + "." + extName);
						pBoard.put("pb_real_path", rFileName);
						pBoard.put("pb_file_path","/upload/"+fileName + "." +extName);
						//사용자가 올린이름, 실제이름을 저장해줘야함.
						fi.write(saveFile);
					}
					//System.out.println("name : " + fileList.get(i).getFieldName());
					//System.out.println("value : " + fileList.get(i).getString("utf-8"));
					//★★★ 참고!! input타입 file 은 위 방식으로 불러올수 없음 ★★★
					//System.out.println(fileList.get(i).isFormField());
					
				}
				if(pbs.insertPBoard(pBoard)==1){
					request.setAttribute("msg","집중햅");
					request.setAttribute("url", "/views/photo-board/insert.jsp");
					RequestDispatcher rd = request.getRequestDispatcher("/views/result.jsp");
					//메세지 전용 jsp 파일 만들어줘서 연결해줌. 
					rd.forward(request,response);
					return;          
				}else {
					
				}
			} catch (FileUploadException e) {
				
				e.printStackTrace();
			} catch (Exception e) {
				
				e.printStackTrace();
			} 
		}else if("update".equals(uri)) {
			
		}else if("delete".equals(uri)) {
			
		}else {
			
		}
		
	}
	public static void main(String[] args) {
		
		
		
	}

}
