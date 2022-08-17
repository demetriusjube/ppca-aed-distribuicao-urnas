package br.jus.tse.distribuicao_urnas.controller;

import br.jus.tse.distribuicao_urnas.domain.CentroDistribuicao;
import br.jus.tse.distribuicao_urnas.domain.Veiculo;
import br.jus.tse.distribuicao_urnas.model.SimulacaoDTO;
import br.jus.tse.distribuicao_urnas.repos.CentroDistribuicaoRepository;
import br.jus.tse.distribuicao_urnas.repos.VeiculoRepository;
import br.jus.tse.distribuicao_urnas.service.SimulacaoService;
import br.jus.tse.distribuicao_urnas.util.WebUtils;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/simulacaos")
public class SimulacaoController {

    private final SimulacaoService simulacaoService;
    private final VeiculoRepository veiculoRepository;
    private final CentroDistribuicaoRepository centroDistribuicaoRepository;

    public SimulacaoController(final SimulacaoService simulacaoService,
            final VeiculoRepository veiculoRepository,
            final CentroDistribuicaoRepository centroDistribuicaoRepository) {
        this.simulacaoService = simulacaoService;
        this.veiculoRepository = veiculoRepository;
        this.centroDistribuicaoRepository = centroDistribuicaoRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("veiculoSimulacaosValues", veiculoRepository.findAll().stream().collect(
                Collectors.toMap(Veiculo::getId, Veiculo::getDescricao)));
        model.addAttribute("centroDistribuicaoValues", centroDistribuicaoRepository.findAll().stream().collect(
                Collectors.toMap(CentroDistribuicao::getId, CentroDistribuicao::getNome)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("simulacaos", simulacaoService.findAll());
        return "simulacao/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("simulacao") final SimulacaoDTO simulacaoDTO) {
        return "simulacao/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("simulacao") @Valid final SimulacaoDTO simulacaoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "simulacao/add";
        }
        simulacaoService.create(simulacaoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("simulacao.create.success"));
        return "redirect:/simulacaos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("simulacao", simulacaoService.get(id));
        return "simulacao/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("simulacao") @Valid final SimulacaoDTO simulacaoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "simulacao/edit";
        }
        simulacaoService.update(id, simulacaoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("simulacao.update.success"));
        return "redirect:/simulacaos";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = simulacaoService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            simulacaoService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("simulacao.delete.success"));
        }
        return "redirect:/simulacaos";
    }

}
