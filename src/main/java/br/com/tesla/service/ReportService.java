package br.com.tesla.service;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class ReportService {
	
	public JasperPrint loadReport(String fileName,Map<String,Object> params, Collection<?> list, HttpServletRequest request) throws JRException{
		JasperReport jasperReport = loadReport(fileName, request);
         
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(list));
        return jasperPrint;		        
	}

	private JasperReport loadReport(String fileName, HttpServletRequest request)throws JRException {
		File reportFile = new File( request.getSession().getServletContext().getRealPath("/WEB-INF/reports/jasper/" + fileName + ".jasper"));
        if (!reportFile.exists()) {
        	JasperCompileManager.compileReportToFile(request.getSession().getServletContext().getRealPath("/WEB-INF/reports/" + fileName + ".jrxml"),
        			                                 request.getSession().getServletContext().getRealPath("/WEB-INF/reports/jasper/" + fileName + ".jasper"));
         }
         JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
		return jasperReport;
	}

}
