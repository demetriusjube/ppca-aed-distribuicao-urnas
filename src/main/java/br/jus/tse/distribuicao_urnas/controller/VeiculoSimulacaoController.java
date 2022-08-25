package br.jus.tse.distribuicao_urnas.controller;

import br.jus.tse.distribuicao_urnas.domain.PlanoRota;
import br.jus.tse.distribuicao_urnas.domain.Simulacao;
import br.jus.tse.distribuicao_urnas.domain.Veiculo;
import br.jus.tse.distribuicao_urnas.model.VeiculoSimulacaoDTO;
import br.jus.tse.distribuicao_urnas.repos.PlanoRotaRepository;
import br.jus.tse.distribuicao_urnas.repos.SimulacaoRepository;
import br.jus.tse.distribuicao_urnas.repos.VeiculoRepository;
import br.jus.tse.distribuicao_urnas.service.VeiculoSimulacaoService;
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
@RequestMapping("/veiculoSimulacaos")
public class VeiculoSimulacaoController {

    private final VeiculoSimulacaoService veiculoSimulacaoService;
    private final SimulacaoRepository simulacaoRepository;
    private final VeiculoRepository veiculoRepository;
    private final PlanoRotaRepository planoRotaRepository;

    public VeiculoSimulacaoController(final VeiculoSimulacaoService veiculoSimulacaoService,
            final SimulacaoRepository simulacaoRepository,
            final VeiculoRepository veiculoRepository,
            final PlanoRotaRepository planoRotaRepository) {
        this.veiculoSimulacaoService = veiculoSimulacaoService;
        this.simulacaoRepository = simulacaoRepository;
        this.veiculoRepository = veiculoRepository;
        this.planoRotaRepository = planoRotaRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("simulacaoValues", simulacaoRepository.findAll().stream().collect(
                Collectors.toMap(Simulacao::getId, Simulacao::getDescricao)));
        model.addAttribute("veiculoValues", veiculoRepository.findAll().stream().collect(
                Collectors.toMap(Veiculo::getId, Veiculo::getDescricao)));
        model.addAttribute("planoRotaValues", planoRotaRepository.findAll().stream().collect(
                Collectors.toMap(PlanoRota::getId, PlanoRota::getId)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("veiculoSimulacaos", veiculoSimulacaoService.findAll());
        return "veiculoSimulacao/list";
    }

    @GetMapping("/add")
    public String add(
            @ModelAttribute("veiculoSimulacao") final VeiculoSimulacaoDTO veiculoSimulacaoDTO) {
        return "veiculoSimulacao/add";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("veiculoSimulacao") @Valid final VeiculoSimulacaoDTO veiculoSimulacaoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "veiculoSimulacao/add";
        }
        veiculoSimulacaoService.create(veiculoSimulacaoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("veiculoSimulacao.create.success"));
        return "redirect:/veiculoSimulacaos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("veiculoSimulacao", veiculoSimulacaoService.get(id));
        return "veiculoSimulacao/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("veiculoSimulacao") @Valid final VeiculoSimulacaoDTO veiculoSimulacaoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "veiculoSimulacao/edit";
        }
        veiculoSimulacaoService.update(id, veiculoSimulacaoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("veiculoSimulacao.update.success"));
        return "redirect:/veiculoSimulacaos";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = veiculoSimulacaoService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            veiculoSimulacaoService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("veiculoSimulacao.delete.success"));
        }
        return "redirect:/veiculoSimulacaos";
    }

}
