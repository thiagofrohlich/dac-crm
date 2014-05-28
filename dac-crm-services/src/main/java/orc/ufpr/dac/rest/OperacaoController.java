package orc.ufpr.dac.rest;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import orc.ufpr.dac.PageSize;
import orc.ufpr.dac.transformer.impl.OperacaoTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ufpr.dac.domain.Operacao;
import org.ufpr.dac.model.OperacaoSummary;
import org.ufpr.dac.model.RelatorioSummary;
import org.ufpr.dac.repository.OperacaoRepository;
import org.ufpr.dac.wrapper.OperacaoWrapper;

@Controller
@RequestMapping("/operacao")
public class OperacaoController {
	
	private final OperacaoTransformer operacaoTransformer;
	private final OperacaoRepository operacaoRepository;

	
	@Autowired
	public OperacaoController(OperacaoTransformer operacaoTransformer,
			OperacaoRepository operacaoRepository) {
		this.operacaoTransformer = operacaoTransformer;
		this.operacaoRepository = operacaoRepository;
	}
	

	
	@ResponseBody
	@RequestMapping(value="/page/{page}", method=RequestMethod.GET)
	public OperacaoWrapper getAll(@PathVariable Integer page) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Pageable pageRequest = new PageRequest(page, PageSize.DEFAULT);
		Page<Operacao> result = operacaoRepository.findAll(pageRequest);
		OperacaoWrapper wrapper = new OperacaoWrapper(result);
		wrapper.setList(new ArrayList<OperacaoSummary>(PageSize.DEFAULT));
		
		for(Operacao operacao : result) {
			OperacaoSummary p = instantiateOperacaoSummary();
			operacaoTransformer.transform(operacao, p);
			p.getNotaFiscal().setObservacao("");
			p.getNotaFiscal().getPessoa().setTelefone("");
			wrapper.getList().add(p);
		}
		
		return wrapper;
	}
	
	@ResponseBody
	@RequestMapping(value="/compras/{dateini}/fim/{datafim}/doc/{cnpj}/produto/{produtoId}", method=RequestMethod.GET )
	public byte[] getCompras(@PathVariable("dateini") String dateini,@PathVariable("datafim") String datafim, @PathVariable("cnpj") String cnpj,
			@PathVariable("produtoId") Long produtoId) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, ParseException {
		List<Operacao> result = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");  
		Date ini = new Date(format.parse(dateini).getTime()); 
		Date fim = new Date(format.parse(datafim).getTime());
		if(cnpj.equals("VAZIO") && produtoId == 0) result = operacaoRepository.buscaCompraRelatorio(ini, fim);
		else if(cnpj.equals("VAZIO") && produtoId != 0)result = operacaoRepository.buscaCompraRelatorio(ini, fim, produtoId);
		else if(!cnpj.equals("VAZIO") && produtoId == 0)result = operacaoRepository.buscaCompraRelatorio(ini, fim, cnpj);
		else if(!cnpj.equals("VAZIO") && produtoId != 0)result = operacaoRepository.buscaCompraRelatorio(ini, fim, cnpj, produtoId);
		List<RelatorioSummary> lstFields = new ArrayList<>();
		for(Operacao op : result){
			RelatorioSummary relatorio = new RelatorioSummary();
			relatorio.setData_operacao(op.getDataOperacao());
			relatorio.setCnpj(op.getNotaFiscal().getPessoa().getPessoaJuridica().getCnpj());
			relatorio.setId(op.getId());
			relatorio.setNome(op.getNotaFiscal().getPessoa().getNome());
			relatorio.setValor_total(op.getValorTotal());
			lstFields.add(relatorio);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("titulo", "COMPRAS");
		format = new SimpleDateFormat("yyyyMMddHHmmss");  
		String id = format.format(new Date());
		try{
			JasperReport pathRxml = JasperCompileManager.compileReport("D:/repo/dac/dac-crm/dac-crm-services/relatorios/relatoriocomrasDataOnly.jrxml");
			JasperPrint printReport = JasperFillManager.fillReport(pathRxml, map, new JRBeanCollectionDataSource(lstFields));
			JasperExportManager.exportReportToPdfFile(printReport,"D:/repo/dac/dac-crm/dac-crm-services/relatorios/relatorio"+id+".pdf");
			File file = new File("D:/repo/dac/dac-crm/dac-crm-services/relatorios/relatorio"+id+".pdf");
			FileInputStream fis = new FileInputStream(file);  
	        byte[] data = new byte[fis.available()];  
	        fis.read(data);  
	        fis.close();
	        return data; 
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/vendas/{dateini}/fim/{datafim}/doc/{cpf}/produto/{produtoId}", method=RequestMethod.GET )
	public  byte[] getVendas(@PathVariable("dateini") String dateini,@PathVariable("datafim") String datafim, @PathVariable("cpf") String cpf,
			@PathVariable("produtoId") Long produtoId) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, ParseException {
		List<Operacao> result = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");  
		Date ini = new Date(format.parse(dateini).getTime()); 
		Date fim = new Date(format.parse(datafim).getTime());
		if(cpf.equals("") && produtoId == 0) result = operacaoRepository.buscaVendaRelatorio(ini, fim);
		else if(cpf.equals("") && produtoId != 0)result = operacaoRepository.buscaVendaRelatorio(ini, fim, produtoId);
		else if(!cpf.equals("") && produtoId == 0)result = operacaoRepository.buscaVendaRelatorio(ini, fim, cpf);
		else if(!cpf.equals("") && produtoId != 0)result = operacaoRepository.buscaVendaRelatorio(ini, fim, cpf, produtoId);
		List<RelatorioSummary> lstFields = new ArrayList<>();
		for(Operacao op : result){
			RelatorioSummary relatorio = new RelatorioSummary();
			relatorio.setData_operacao(op.getDataOperacao());
			relatorio.setCnpj(op.getNotaFiscal().getPessoa().getPessoaJuridica().getCnpj());
			relatorio.setId(op.getId());
			relatorio.setNome(op.getNotaFiscal().getPessoa().getNome());
			relatorio.setValor_total(op.getValorTotal());
			lstFields.add(relatorio);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("titulo", "VENDAS");
		format = new SimpleDateFormat("yyyyMMddHHmmss");  
		String id = format.format(new Date());
		try{
			JasperReport pathRxml = JasperCompileManager.compileReport("D:/repo/dac/dac-crm/dac-crm-services/relatorios/relatoriocomrasDataOnly.jrxml");
			JasperPrint printReport = JasperFillManager.fillReport(pathRxml, map, new JRBeanCollectionDataSource(lstFields));
			JasperExportManager.exportReportToPdfFile(printReport,"D:/repo/dac/dac-crm/dac-crm-services/relatorios/relatorio"+id+".pdf");
			File file = new File("D:/repo/dac/dac-crm/dac-crm-services/relatorios/relatorio"+id+".pdf");
			FileInputStream fis = new FileInputStream(file);  
	        byte[] data = new byte[fis.available()];  
	        fis.read(data);  
	        fis.close();
	        return data; 
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public OperacaoSummary getOne(@PathVariable Long id) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Operacao result = operacaoRepository.findOne(id);
		OperacaoSummary summary = instantiateOperacaoSummary();
		operacaoTransformer.transform(result, summary);
		
		return summary;
	}
	
	
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public OperacaoSummary create(@RequestBody OperacaoSummary operacao) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		return saveOrUpdate(operacao);
	}

	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT)
	public OperacaoSummary update(@RequestBody OperacaoSummary operacao) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		return saveOrUpdate(operacao);
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public boolean delete(@PathVariable Long id) {
		operacaoRepository.delete(id);
		return operacaoRepository.findOne(id) == null;
	}

	private OperacaoSummary saveOrUpdate(OperacaoSummary operacao) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Operacao op = new Operacao();
		operacaoTransformer.transform(operacao, op);
		
		op = operacaoRepository.save(op);
		
		OperacaoSummary summary = instantiateOperacaoSummary();
//		operacaoTransformer.transform(op, summary);
		return summary;
	}

	private OperacaoSummary instantiateOperacaoSummary() {
		return new OperacaoSummary();
	}

}
